package menugps;

public class Notes {
    private String titulo;
    private String descricao;
    private String notas;
    
    public Notes(){
    }
    
    public Notes(String titulo){
        this.titulo = titulo;
    }
    
    public Notes(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }
    
    public void setNotas(String note){
        this.notas += note;
    }
    
    public String getNotas(){
        return notas;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setDescricao(String descri){
        this.descricao = descri;
    }
    
    public String getDescricao(){
        return descricao;
    }
}
