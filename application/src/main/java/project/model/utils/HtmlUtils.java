package model.utils;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

    public static String getHref(String contenu) {
        String input = extractH1Content(contenu);

        // 1. Minuscule
        String slug = input.toLowerCase();

        // 2. Supprimer les accents
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 3. Supprimer caractères spéciaux
        slug = slug.replaceAll("[^a-z0-9\\s-]", "");

        // 4. Remplacer espaces par tirets
        slug = slug.replaceAll("\\s+", "-");

        // 5. Nettoyer les tirets en trop
        slug = slug.replaceAll("-{2,}", "-");

        return slug.trim();
    }
    
    /**
     * Extrait le contenu de la balise h1 avec gestion des caractères spéciaux
     */


    public static String extractH1Content(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        
        try {
            // Pattern pour h1 avec ou sans attributs
            Pattern pattern = Pattern.compile(
                "<h1\\b[^>]*>(.*?)</h1>",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE
            );
            
            Matcher matcher = pattern.matcher(html);
            
            if (matcher.find()) {
                String content = matcher.group(1);
                
                // Nettoyer le contenu
                content = content.replaceAll("\\s+", " ");  // Espaces multiples
                content = content.replaceAll("&nbsp;", " ");  // Espaces HTML
                content = content.replaceAll("&amp;", "&");  // Symbole &
                content = content.replaceAll("&lt;", "<");   // Symbole <
                content = content.replaceAll("&gt;", ">");   // Symbole >
                content = content.replaceAll("<[^>]*>", ""); // Enlever les balises restantes
                
                return content.trim();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'extraction du h1: " + e.getMessage());
        }
        
        return "";
    }
}