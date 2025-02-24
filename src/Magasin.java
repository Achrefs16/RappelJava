import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private int id;
    private String name;
    private List<Article> articles;

    public Magasin(int id, String name) {
        this.id = id;
        this.name = name;
        this.articles = new ArrayList<>();
    }

    public void add(Article article) {
        articles.add(article);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void afficherArticles() {
        System.out.println("🏪 Magasin: " + name);
        if (articles.isEmpty()) {
            System.out.println("   (No articles available)");
        } else {
            for (Article a : articles) {
                System.out.println("   " + a);
            }
        }
    }
}
