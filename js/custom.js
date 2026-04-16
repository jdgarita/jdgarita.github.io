/* ============================================================
   Juan Diego Garita — custom.js
   ============================================================
   Vanilla-JS controllers for:
     1. Theme toggle  (light / dark, persisted in localStorage)
     2. Language toggle (en / es, persisted in localStorage)
     3. i18n loader    (fetches i18n/<lang>.json; falls back to
                        embedded dictionary on file:// previews)
     4. Mobile nav toggle
     5. Header shadow on scroll
     6. Footer year

   i18n contract:
     - Every translatable element carries [data-i18n="key"].
       The loader sets its textContent to dict[key].
     - Attribute translation uses [data-i18n-attr="attr:key"].
       Multiple pairs are comma-separated:
         data-i18n-attr="aria-label:a11y.foo, title:a11y.foo"

   Theme initialization runs inline in index.html <head> to avoid
   FOUC; this file only handles the click-to-toggle interaction.
   ============================================================ */

(function () {
    'use strict';

    /* ---------- Fallback dictionaries (used when fetch fails,
       e.g. opening index.html via file://). Keep in sync with
       i18n/en.json and i18n/es.json. ---------- */
    var FALLBACK = {
        en: {
            'meta.title': 'Juan Diego Garita — Senior Android / KMP Engineer',
            'meta.description': 'Senior mobile engineer with 10+ years of experience building Android and Kotlin Multiplatform apps.',
            'a11y.themeToggle': 'Toggle light / dark theme',
            'a11y.langToggle': 'Switch language',
            'a11y.menuToggle': 'Toggle navigation menu',
            'nav.about': 'About',
            'nav.experience': 'Experience',
            'nav.projects': 'Projects',
            'nav.skills': 'Skills',
            'nav.contact': 'Contact',
            'nav.resume': 'Résumé',
            'hero.eyebrow': 'Senior Mobile Engineer',
            'hero.title': 'Juan Diego Garita',
            'hero.tagline': 'I build polished, reliable mobile apps with Kotlin Multiplatform.',
            'hero.description': '10+ years shipping production mobile apps for startups and large companies across ads, health and authentication.',
            'hero.ctaPrimary': 'Download résumé',
            'hero.ctaSecondary': 'Get in touch',
            'about.heading': 'About',
            'about.body': "I'm a Google Certified Associate Android Developer in Kotlin with 10+ years of experience writing clean, well-tested code for startups and large companies. I focus on Kotlin Multiplatform, Jetpack Compose and modern Android, and I care deeply about craftsmanship, collaboration and continuous improvement.",
            'about.highlight1': 'Google Certified Associate Android Developer (Kotlin)',
            'about.highlight2': 'Kotlin Multiplatform and Jetpack Compose specialist',
            'about.highlight3': 'Agile practitioner — Scrum, Kanban, TDD, pair programming',
            'experience.heading': 'Experience Summary',
            'experience.subheading': '',
            'experience.role1.company': 'Swiftly',
            'experience.role1.role': 'Senior Kotlin Multiplatform Engineer',
            'experience.role1.dates': '2022 — Present',
            'experience.role1.bullet1': 'Designed a reusable Ads component inside a Kotlin Multiplatform "App Factory", enabling fast rollout of consistent monetization across multiple white-label client apps.',
            'experience.role1.bullet2': 'Integrated the Xandr Mobile SDK into the shared KMP architecture for seamless ad delivery and inventory management.',
            'experience.role2.company': 'Mode',
            'experience.role2.role': 'Senior Android Developer',
            'experience.role2.dates': '2021 — 2022',
            'experience.role2.bullet1': 'Authored best-practice, standards, and architecture documentation for the mobile team.',
            'experience.role2.bullet2': 'Partnered with Product to ship new features validated through A/B testing.',
            'experience.role2.bullet3': 'Orchestrated releases across multiple teams and tracked bugs to keep each release UX-solid.',
            'experience.role3.company': 'BodyBuilding.com',
            'experience.role3.role': 'Senior Android Developer',
            'experience.role3.dates': '2021',
            'experience.role3.bullet1': 'Migrated existing MVP logic written in Java to MVVM in Kotlin.',
            'experience.role3.bullet2': 'Diagnosed and resolved critical performance bottlenecks, improving overall app stability.',
            'experience.role3.bullet3': 'Collaborated cross-functionally to keep the Android app healthy during a tight timeline.',
            'experience.role4.company': 'Trusona',
            'experience.role4.role': 'Android Developer',
            'experience.role4.dates': '2017 — 2021',
            'experience.role4.bullet1': 'Wrote production Java and Kotlin code with TDD, pair programming, and clean-code practices.',
            'experience.role4.bullet2': 'Partnered with the experience team on new features delivered via a Kanban workflow.',
            'experience.role4.bullet3': 'Automated the release scheduling pipeline with Fastlane together with the product team.',
            'projects.heading': 'Personal Projects',
            'projects.subheading': '',
            'projects.freshtrack.tagline': 'Food expiration tracker',
            'projects.freshtrack.description': "Track what's in your fridge and pantry, get timely reminders before food expires, and cut down on waste.",
            'projects.store.getItOn': 'Get it on',
            'projects.store.availableOn': 'Available on',
            'projects.store.comingSoon': 'App Store — Coming soon',
            'skills.heading': 'Tech stack',
            'skills.subheading': '',
            'skills.group.core': 'Core',
            'skills.group.android': 'Android',
            'skills.group.tooling': 'Tooling',
            'skills.group.architecture': 'Architecture',
            'contact.heading': "Let's talk",
            'contact.body': 'Open to senior Android and Kotlin Multiplatform roles, contract work and interesting collaborations.',
            'contact.emailLabel': 'Email',
            'contact.linkedinLabel': 'LinkedIn',
            'contact.githubLabel': 'GitHub',
            'footer.rights': 'All rights reserved.'
        },
        es: {
            'meta.title': 'Juan Diego Garita — Ingeniero Senior Android / KMP',
            'meta.description': 'Ingeniero móvil senior con más de 10 años de experiencia construyendo apps Android y Kotlin Multiplatform.',
            'a11y.themeToggle': 'Cambiar tema claro / oscuro',
            'a11y.langToggle': 'Cambiar idioma',
            'a11y.menuToggle': 'Abrir menú de navegación',
            'nav.about': 'Sobre Mí',
            'nav.experience': 'Experiencia',
            'nav.projects': 'Proyectos',
            'nav.skills': 'Habilidades',
            'nav.contact': 'Contacto',
            'nav.resume': 'Currículum',
            'hero.eyebrow': 'Ingeniero Móvil Senior',
            'hero.title': 'Juan Diego Garita',
            'hero.tagline': 'Construyo apps móviles pulidas y confiables con Kotlin Multiplatform.',
            'hero.description': 'Más de 10 años lanzando apps móviles en producción para startups y grandes empresas en publicidad, salud y autenticación.',
            'hero.ctaPrimary': 'Descargar currículum',
            'hero.ctaSecondary': 'Contáctame',
            'about.heading': 'Sobre Mí',
            'about.body': 'Soy un Google Certified Associate Android Developer en Kotlin con más de 10 años de experiencia escribiendo código limpio y bien probado para startups y grandes empresas. Me enfoco en Kotlin Multiplatform, Jetpack Compose y Android moderno, y me apasionan el buen oficio, la colaboración y la mejora continua.',
            'about.highlight1': 'Google Certified Associate Android Developer (Kotlin)',
            'about.highlight2': 'Especialista en Kotlin Multiplatform y Jetpack Compose',
            'about.highlight3': 'Prácticas ágiles — Scrum, Kanban, TDD, pair programming',
            'experience.heading': 'Resumen de Experiencia',
            'experience.subheading': '',
            'experience.role1.company': 'Swiftly',
            'experience.role1.role': 'Ingeniero Senior de Kotlin Multiplatform',
            'experience.role1.dates': '2022 — Presente',
            'experience.role1.bullet1': 'Diseñé un componente de anuncios reutilizable dentro de un "App Factory" de Kotlin Multiplatform, permitiendo lanzar de forma rápida funciones de monetización consistentes en múltiples apps white-label.',
            'experience.role1.bullet2': 'Integré el SDK móvil de Xandr en la arquitectura KMP compartida para una entrega de anuncios e inventario sin fricciones.',
            'experience.role2.company': 'Mode',
            'experience.role2.role': 'Desarrollador Android Senior',
            'experience.role2.dates': '2021 — 2022',
            'experience.role2.bullet1': 'Redacté documentación de buenas prácticas, estándares y guías de arquitectura para el equipo móvil.',
            'experience.role2.bullet2': 'Colaboré con el equipo de Producto para lanzar nuevas funciones validadas con A/B testing.',
            'experience.role2.bullet3': 'Coordiné releases entre múltiples equipos y dí seguimiento a bugs para mantener una UX sólida en cada versión.',
            'experience.role3.company': 'BodyBuilding.com',
            'experience.role3.role': 'Desarrollador Android Senior',
            'experience.role3.dates': '2021',
            'experience.role3.bullet1': 'Migré la lógica existente en MVP (Java) a MVVM en Kotlin.',
            'experience.role3.bullet2': 'Diagnostiqué y resolví cuellos de botella de rendimiento críticos, mejorando la estabilidad general de la app.',
            'experience.role3.bullet3': 'Colaboré con equipos multifuncionales para mantener la app Android saludable en un plazo ajustado.',
            'experience.role4.company': 'Trusona',
            'experience.role4.role': 'Desarrollador Android',
            'experience.role4.dates': '2017 — 2021',
            'experience.role4.bullet1': 'Escribí código de producción en Java y Kotlin aplicando TDD, pair programming y principios de clean code.',
            'experience.role4.bullet2': 'Colaboré con el equipo de experiencia en nuevas funciones entregadas mediante un flujo Kanban.',
            'experience.role4.bullet3': 'Automaticé el pipeline de publicación con Fastlane junto al equipo de producto.',
            'projects.heading': 'Proyectos Personales',
            'projects.subheading': '',
            'projects.freshtrack.tagline': 'Control de caducidad de alimentos',
            'projects.freshtrack.description': 'Lleva el control de lo que hay en tu refri y alacena, recibe recordatorios antes de que los alimentos caduquen y reduce el desperdicio.',
            'projects.store.getItOn': 'Disponible en',
            'projects.store.availableOn': 'Disponible en',
            'projects.store.comingSoon': 'App Store — Próximamente',
            'skills.heading': 'Stack técnico',
            'skills.subheading': '',
            'skills.group.core': 'Base',
            'skills.group.android': 'Android',
            'skills.group.tooling': 'Herramientas',
            'skills.group.architecture': 'Arquitectura',
            'contact.heading': 'Hablemos',
            'contact.body': 'Abierto a roles senior de Android y Kotlin Multiplatform, trabajo por contrato y colaboraciones interesantes.',
            'contact.emailLabel': 'Correo',
            'contact.linkedinLabel': 'LinkedIn',
            'contact.githubLabel': 'GitHub',
            'footer.rights': 'Todos los derechos reservados.'
        }
    };

    /* ---------- Helpers ---------- */
    function $(sel, root) { return (root || document).querySelector(sel); }
    function $$(sel, root) { return Array.prototype.slice.call((root || document).querySelectorAll(sel)); }

    /* ---------- i18n ---------- */
    function loadDict(lang) {
        // Try fetch; fall back to embedded dictionary on failure (file://, offline, etc.)
        return fetch('i18n/' + lang + '.json', { cache: 'no-cache' })
            .then(function (res) {
                if (!res.ok) throw new Error('fetch failed: ' + res.status);
                return res.json();
            })
            .catch(function () { return FALLBACK[lang] || FALLBACK.en; });
    }

    function applyDict(dict) {
        $$('[data-i18n]').forEach(function (el) {
            var key = el.getAttribute('data-i18n');
            if (dict[key] != null) el.textContent = dict[key];
        });

        $$('[data-i18n-attr]').forEach(function (el) {
            el.getAttribute('data-i18n-attr').split(',').forEach(function (pair) {
                var parts = pair.split(':');
                if (parts.length !== 2) return;
                var attr = parts[0].trim();
                var key  = parts[1].trim();
                if (dict[key] != null) el.setAttribute(attr, dict[key]);
            });
        });
    }

    function setLang(lang) {
        document.documentElement.setAttribute('lang', lang);
        try { localStorage.setItem('lang', lang); } catch (e) {}
        var label = $('#lang-toggle-label');
        // Show the language the button will switch *to*, not the current one.
        if (label) label.textContent = lang === 'es' ? 'EN' : 'ES';
        loadDict(lang).then(applyDict);
    }

    /* ---------- Theme ---------- */
    function setTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        try { localStorage.setItem('theme', theme); } catch (e) {}
        var meta = document.querySelector('meta[name="theme-color"]:not([media])') ||
                   document.querySelector('meta[name="theme-color"]');
        if (meta) meta.setAttribute('content', theme === 'dark' ? '#0e1116' : '#fafaf7');
    }

    /* ---------- Boot ---------- */
    document.addEventListener('DOMContentLoaded', function () {
        // Initial language (the inline head script already set <html lang>)
        var lang = document.documentElement.getAttribute('lang') || 'en';
        if (lang !== 'en' && lang !== 'es') lang = 'en';
        setLang(lang);

        // Theme toggle
        var themeBtn = $('#theme-toggle');
        if (themeBtn) {
            themeBtn.addEventListener('click', function () {
                var current = document.documentElement.getAttribute('data-theme') === 'dark' ? 'dark' : 'light';
                setTheme(current === 'dark' ? 'light' : 'dark');
            });
        }

        // Language toggle
        var langBtn = $('#lang-toggle');
        if (langBtn) {
            langBtn.addEventListener('click', function () {
                var current = document.documentElement.getAttribute('lang') === 'es' ? 'es' : 'en';
                setLang(current === 'es' ? 'en' : 'es');
            });
        }

        // Mobile nav toggle
        var navToggle = $('#nav-toggle');
        var navMenu   = $('#nav-menu');
        if (navToggle && navMenu) {
            navToggle.addEventListener('click', function () {
                var open = navMenu.classList.toggle('is-open');
                navToggle.setAttribute('aria-expanded', String(open));
            });
            // Close menu when tapping a link on mobile
            navMenu.addEventListener('click', function (e) {
                if (e.target.tagName === 'A' && navMenu.classList.contains('is-open')) {
                    navMenu.classList.remove('is-open');
                    navToggle.setAttribute('aria-expanded', 'false');
                }
            });
        }

        // Header shadow on scroll
        var header = $('.site-header');
        if (header) {
            var onScroll = function () {
                header.classList.toggle('is-scrolled', window.scrollY > 8);
            };
            window.addEventListener('scroll', onScroll, { passive: true });
            onScroll();
        }

        // Footer year
        var yearEl = $('#footer-year');
        if (yearEl) yearEl.textContent = String(new Date().getFullYear());
    });
})();
