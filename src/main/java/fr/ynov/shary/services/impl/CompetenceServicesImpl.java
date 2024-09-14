package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.Competence;
import fr.ynov.shary.repository.CompetenceRepository;
import fr.ynov.shary.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class CompetenceServicesImpl implements CompetenceService {

    @Autowired
    private CompetenceRepository  competenceRepository;
    @Override
    public long createCompetence(Competence competence) {
        Competence savedCompetence = competenceRepository.save(competence);
        return  savedCompetence.getComptence_id();
    }
    @Override
    public void deleteCompetence(long id) {
        competenceRepository.deleteById(id);
    }
    @Override
    public long updateCompetence(Competence competence) {
        Competence updatedCompetence = competenceRepository.save(competence);
        return updatedCompetence.getComptence_id();
    }
    @Override
    public Competence getCompetence(long id) {
        return competenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Competence> getAll() {
        return competenceRepository.findAll();
    }

    public void addCompetencesIfEmpty() {
        if (competenceRepository.count() == 0) {
            List<Competence> competences = Arrays.asList(
                    // Langages de programmation
                    new Competence(0, "Java", "Langage de programmation orienté objet utilisé pour le développement d’applications back-end.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Python", "Langage de programmation interprété, utilisé pour le développement web, l’IA et l’automatisation.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "JavaScript", "Langage de programmation pour le développement web côté client et serveur.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "C++", "Langage de programmation utilisé pour les systèmes embarqués et les applications à haute performance.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "C#", "Langage de programmation orienté objet développé par Microsoft pour les applications .NET.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Ruby", "Langage de programmation utilisé principalement pour le développement web avec Ruby on Rails.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Go", "Langage de programmation développé par Google pour la performance et la concurrence.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Kotlin", "Langage de programmation principalement utilisé pour le développement Android.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Swift", "Langage de programmation pour le développement d'applications iOS et macOS.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "PHP", "Langage de programmation pour le développement de sites web dynamiques.", false, new Date(), new Date(), new HashSet<>(), null),

                    // Frameworks front-end
                    new Competence(0, "Angular", "Framework JavaScript utilisé pour le développement de front-end d’applications web.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "React", "Bibliothèque JavaScript pour construire des interfaces utilisateur dynamiques.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Vue.js", "Framework JavaScript progressif pour construire des interfaces utilisateur.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Svelte", "Framework JavaScript pour la création d’interfaces utilisateur réactives.", false, new Date(), new Date(), new HashSet<>(), null),

                    // Frameworks back-end
                    new Competence(0, "Spring Boot", "Framework Java pour créer des applications web et des services RESTful.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Django", "Framework web Python pour créer des applications rapidement et efficacement.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Flask", "Framework web minimaliste en Python pour les petites applications.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Ruby on Rails", "Framework web Ruby pour créer des applications rapidement.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Express.js", "Framework minimaliste pour construire des applications web avec Node.js.", false, new Date(), new Date(), new HashSet<>(), null),

                    // Bases de données
                    new Competence(0, "MySQL", "Système de gestion de base de données relationnelle.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "PostgreSQL", "Système de gestion de base de données relationnelle open-source.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "MongoDB", "Base de données NoSQL orientée document.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "SQLite", "Système de gestion de base de données légère intégré dans les applications mobiles et web.", false, new Date(), new Date(), new HashSet<>(), null),

                    // Outils de développement
                    new Competence(0, "Git", "Système de contrôle de version distribué pour suivre les modifications du code source.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Docker", "Plateforme de conteneurisation permettant d’emballer des applications dans des conteneurs.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Kubernetes", "Système d’orchestration pour automatiser le déploiement, la gestion et la mise à l’échelle des conteneurs.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Jenkins", "Serveur d’automatisation open-source pour l’intégration continue et le déploiement continu.", false, new Date(), new Date(), new HashSet<>(), null),

                    // Outils et technologies cloud
                    new Competence(0, "AWS", "Services web d’Amazon, un ensemble de services cloud pour le développement et le déploiement d’applications.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Azure", "Services cloud de Microsoft pour le développement, le test, et le déploiement d’applications.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Google Cloud", "Suite de services cloud offerts par Google.", false, new Date(), new Date(), new HashSet<>(), null),
                    new Competence(0, "Heroku", "Plateforme cloud en tant que service (PaaS) pour déployer des applications.", false, new Date(), new Date(), new HashSet<>(), null)
            );

            competenceRepository.saveAll(competences);
        }
    }

}
