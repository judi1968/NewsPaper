package model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {
    
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