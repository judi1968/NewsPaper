
# Documentation : Réécriture d'URL avec Tuckey URLRewriteFilter

## 1. Introduction à la Réécriture d'URL

La réécriture d'URL est une technique qui consiste à modifier l'apparence d'une URL sans changer la ressource réelle à laquelle elle correspond. Cela permet de rendre les URLs plus lisibles, plus faciles à mémoriser et plus favorables au référencement (SEO).

Par exemple, une URL complexe comme `http://example.com/products.jsp?category=12&id=345` peut être réécrite en une URL plus simple et plus descriptive comme `http://example.com/produits/electronique/television-345`.

## 2. Avantages de la Réécriture d'URL

- **Amélioration du SEO** : Les moteurs de recherche préfèrent les URLs courtes, descriptives et contenant des mots-clés.
- **Meilleure expérience utilisateur** : Les URLs simples sont plus faciles à lire, à comprendre et à partager.
- **Maintenance facilitée** : Vous pouvez changer la structure de votre site ou la technologie sous-jacente sans casser les liens existants.
- **Sécurité** : Masquer les technologies utilisées (comme `.jsp`) peut réduire les risques de certaines attaques.
- **Redirection** : Gérer les redirections (par exemple, pour du contenu déplacé) de manière propre.

## 3. Installation et Configuration de Tuckey URLRewriteFilter

Tuckey URLRewriteFilter est un filtre Java qui s'intègre à votre application web pour permettre la réécriture d'URL.

### Étape 1 : Ajouter la dépendance

Ajoutez le fichier `urlrewritefilter-x.x.x.jar` dans le répertoire `WEB-INF/lib` de votre application. Si vous utilisez Maven, ajoutez la dépendance suivante à votre `pom.xml` :

```xml
<dependency>
    <groupId>org.tuckey</groupId>
    <artifactId>urlrewritefilter</artifactId>
    <version>5.1.3</version> <!-- Vérifiez la dernière version -->
</dependency>
```

### Étape 2 : Configurer le filtre dans `web.xml`

Ajoutez la configuration du filtre dans votre fichier `WEB-INF/web.xml`. Il est important de le placer avant les mappings de servlets.

```xml
<filter>
    <filter-name>UrlRewriteFilter</filter-name>
    <filter-class>org.tuckey.web.filters.urlrewrite.UrlRewriteFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>UrlRewriteFilter</filter-name>
    <url-pattern>/*</url-pattern>
    <dispatcher>REQUEST</dispatcher>
    <dispatcher>FORWARD</dispatcher>
</filter-mapping>
```

### Étape 3 : Créer le fichier de règles `urlrewrite.xml`

Créez un fichier nommé `urlrewrite.xml` dans le répertoire `WEB-INF`. C'est dans ce fichier que vous définirez vos règles de réécriture.

```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE urlrewrite
    PUBLIC "-//tuckey.org//DTD UrlRewrite 4.0//EN"
    "http://www.tuckey.org/res/dtds/urlrewrite4.0.dtd">

<urlrewrite>

    <!-- Vos règles de réécriture iront ici -->

</urlrewrite>
```

## 4. Règles de Réécriture de Base

Les règles sont définies à l'intérieur de la balise `<urlrewrite>`. Chaque règle (`<rule>`) contient une condition (`<from>`) et une action (`<to>`).

### Exemple 1 : URL simple et propre

Pour transformer `/produit.jsp?id=123` en `/produit/123`.

```xml
<rule>
    <from>^/produit/([0-9]+)$</from>
    <to>/produit.jsp?id=$1</to>
</rule>
```

- `<from>` : Utilise une expression régulière pour capturer l'ID du produit.
  - `^` : Début de l'URL.
  - `/produit/` : Correspond littéralement.
  - `([0-9]+)` : Capture un ou plusieurs chiffres (l'ID).
  - `$` : Fin de l'URL.
- `<to>` : L'URL vers laquelle le serveur redirige en interne.
  - `$1` est une référence à la première capture de l'expression régulière (l'ID).

### Exemple 2 : Redirection permanente (301)

Pour rediriger une ancienne page vers une nouvelle.

```xml
<rule>
    <from>^/ancienne-page.html$</from>
    <to type="permanent-redirect">/nouvelle-page.html</to>
</rule>
```

- `type="permanent-redirect"` envoie un code de statut HTTP 301, indiquant aux navigateurs et aux moteurs de recherche que la page a été déplacée de façon permanente.

## 5. Règles de Réécriture Avancées

### Conditions (`<condition>`)

Vous pouvez ajouter des conditions pour que les règles ne s'appliquent que dans certains cas.

```xml
<rule>
    <from>^/admin/(.*)$</from>
    <condition name="host" operator="notequal">^admin\.example\.com$</condition>
    <to type="forbidden"/>
</rule>
```
Cette règle bloque l'accès au répertoire `/admin/` si le nom d'hôte n'est pas `admin.example.com`.

### Variables et Fonctions

Tuckey URLRewriteFilter expose de nombreuses variables (comme `{method}`, `{host}`, `{header:user-agent}`) et des fonctions (comme `lower`, `escape`) que vous pouvez utiliser dans vos règles.

## 6. Dépannage et Débogage

Tuckey URLRewriteFilter fournit une page de statut pour vous aider à déboguer vos règles. Pour l'activer, ajoutez ceci à votre `web.xml` dans la configuration du filtre :

```xml
<filter>
    <filter-name>UrlRewriteFilter</filter-name>
    <filter-class>org.tuckey.web.filters.urlrewrite.UrlRewriteFilter</filter-class>
    <init-param>
        <param-name>statusPath</param-name>
        <param-value>/rewrite-status</param-value>
    </init-param>
    <init-param>
        <param-name>logLevel</param-name>
        <param-value>DEBUG</param-value>
    </init-param>
</filter>
```

Vous pourrez ensuite accéder à `http://votresite.com/rewrite-status` (accessible uniquement depuis `localhost` par défaut pour des raisons de sécurité) pour voir l'état du filtre, les règles chargées et les logs de réécriture.

Pour plus d'informations et d'exemples, consultez la [documentation officielle de Tuckey URLRewriteFilter](http://www.tuckey.org/urlrewrite/).
