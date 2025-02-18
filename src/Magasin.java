// Magasin.java
import java.util.ArrayList;

public class Magasin {
    private ArrayList<Article> articles;

    public Magasin() {
        this.articles = new ArrayList<>();
    }

    public void add(Article article) {
        articles.add(article);
    }

    public void afficherArticles() {
        for (Article a : articles) {
            System.out.println(a);
        }
    }
}
