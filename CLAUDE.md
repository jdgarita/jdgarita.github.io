# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A plain static personal site for `jdgarita.dev`, served by GitHub Pages directly from the repo root. `index.html` is the entry point; everything is hand-written vanilla HTML/CSS/JS. The only third-party CSS loaded is **FontAwesome 4.7.0** (vendored, for icons) and Google Fonts (Inter + JetBrains Mono). There is no Bootstrap, no jQuery, no build step, no package manager, no tests, no CI. Edits to the source files are the deliverable — open `index.html` in a browser to preview.

Page sections, in order: Hero → About → Experience → Projects → Skills → Contact. Experience and Skills alternate on the `section-alt` background so the banding stays consistent.

## Branches and deployment

- `master` is the published branch. Pushing to `master` deploys via GitHub Pages.
- `legacy` is the working branch for the 2026 redesign.
- There is no deploy script; `git push` to `master` is the deploy.

## Repo layout

Tracked, load-bearing:
- `index.html` — single-page site, all sections inline, semantic landmarks
- `frnk/index.html` — sub-page for the frnk project, reuses the root `css/` and `js/`
- `css/custom.css` — token-driven stylesheet (CSS custom properties)
- `js/custom.js` — theme toggle, language toggle, i18n loader, mobile nav
- `i18n/en.json`, `i18n/es.json` — all user-visible copy (root + sub-pages share one dictionary)
- `css/font-awesome.min.css` + `fonts/fontawesome-*` — FontAwesome 4.7.0 (includes `.woff2`)
- `image/jd.png`, `image/frnk.png`, `image/android.ico` — profile photo, frnk logo, and favicon (sub-pages reference these via `../image/…`)
- `resume/jd.pdf` — downloadable resume
- `.github/workflows/claude-code-review.yml`, `claude.yml` — GitHub Actions that run Claude Code on PRs (no app-side CI)

Untracked and **should be ignored** — leftovers from an abandoned Kobweb (Kotlin/JS) rewrite and an old Firebase experiment. Do not treat these as the project:
- `site/`, `build/`, `.gradle/`, `kotlin-js-store/`, `node_modules/`

If a task mentions Kotlin, Kobweb, Compose, or Firebase, confirm with the user before touching `site/` — it's stale and the likely intent is the static site at the repo root.

## Working on the site

- **Preview locally with HTTP** (recommended): `python3 -m http.server 8000` from the repo root, then open `http://localhost:8000`. This matches how GitHub Pages serves the site and lets `fetch('i18n/en.json')` succeed.
- **Preview via `file://`** also works — `js/custom.js` embeds a fallback dictionary for when `fetch` is blocked by the browser's file-URL policy.
- Styles belong in `css/custom.css`. The palette, spacing, and type scale are controlled by CSS custom properties on `:root` (light) and `:root[data-theme="dark"]` (dark). Change a token once and the whole site updates.
- Scripts belong in `js/custom.js`. No other JS files should exist.

## Editing content

Every user-visible string lives in `i18n/en.json` and `i18n/es.json`, keyed by flat dot-paths (e.g. `hero.title`, `experience.role1.bullet1`). To change something on the page:

1. Find the key in `i18n/en.json`, edit the English value.
2. Make the matching edit in `i18n/es.json` so both languages stay in sync.
3. Reload the browser — no build step.

Structural rules:
- Add a new translatable element by giving it `data-i18n="some.key"` in `index.html`, then define `some.key` in **both** JSON files.
- To translate an attribute (e.g. `aria-label`), use `data-i18n-attr="aria-label:a11y.foo"`. Multiple pairs are comma-separated.
- Keys **must** exist in both `en.json` and `es.json`. Missing keys silently fall through to whatever text is hard-coded in `index.html`.
- Never hard-code user-visible strings in `index.html` — always route through i18n.
- Whenever you add or rename a key, mirror the change in `FALLBACK.en` / `FALLBACK.es` inside `js/custom.js` — they are used as the dictionary when `fetch('i18n/*.json')` fails (e.g. `file://` previews).

Experience content comes from `resume/jd.pdf` — Swiftly, Mode, BodyBuilding.com, Trusona. Update the JSON when the PDF changes, don't introduce placeholders.

## Sub-pages

Sub-pages (currently just `frnk/index.html`) reuse the root `css/custom.css` and `js/custom.js` so a single dictionary, theme, and analytics setup serve every page.

- Reference assets with relative paths: `../css/custom.css`, `../js/custom.js`, `../image/android.ico`.
- Set `<html lang="…" data-i18n-base="../">` so `js/custom.js` resolves `fetch('../i18n/en.json')` instead of looking next to the sub-page.
- Mirror the no-FOUC inline theme/lang script from the root `index.html` `<head>` so first paint matches.
- Add new sub-page strings under a namespaced prefix in **both** `i18n/en.json` and `i18n/es.json` (e.g. `frnk.hero.title`), and mirror them in `FALLBACK.en` / `FALLBACK.es` inside `js/custom.js`.

## Theme & language toggles

- Theme (`light` / `dark`) is stored in `localStorage.theme` and reflected on `<html data-theme=…>`. An inline `<script>` in `index.html` `<head>` applies the stored theme before first paint to prevent FOUC. The button in the header toggles it.
- Language (`en` / `es`) is stored in `localStorage.lang` and reflected on `<html lang=…>`. First-visit default: browser language if it starts with `es`, otherwise English. The button in the header toggles it.

## Accessibility

- Keep semantic landmarks (`<header>`, `<main>`, `<nav>`, `<section aria-labelledby>`, `<footer>`).
- Every icon-only button must have an `aria-label` (use `data-i18n-attr` so it's translated).
- Use the existing `:focus-visible` ring — don't disable outlines.

## Analytics

Firebase Analytics (GA4 backend) is loaded via the **compat CDN** (v11.6.0) — two `<script defer>` tags in `index.html` before `custom.js`. Firebase project: **jdgarita-site** (Firebase Console: `console.firebase.google.com/project/jdgarita-site`).

- Initialization and the `logEvent` helper live in `js/custom.js` inside the IIFE, guarded by `typeof firebase !== 'undefined'` so the site degrades gracefully if the SDK is blocked (ad blockers, `file://`).
- Pageviews are tracked automatically on init.
- Custom events: `file_download` (resume), `select_content` (nav sections, contact links, store link), `theme_toggle`, `lang_toggle`, `mobile_nav_toggle`.
- The Firebase web API key is safe to commit — it's restricted by domain, not a secret.

## Icons

FontAwesome 4.7.0 covers almost everything (`fa-github`, `fa-linkedin`, `fa-apple`, `fa-envelope`, `fa-bars`, `fa-moon-o` / `fa-sun-o`, etc.). **`fa-google-play` does not exist in the FA4 line** — not even 4.7.0. The Google Play button uses an inline SVG path (from Simple Icons) that inherits `currentColor`. If you need another brand icon that FA4 lacks, follow the same inline-SVG pattern rather than upgrading the icon library.
