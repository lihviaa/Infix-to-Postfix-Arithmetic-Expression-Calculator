
@SuppressWarnings("unchecked")

public class Pilha<T>{
    static final int CAPACIDADE_PILHA = 128;
    private int contador;
    private T[] pilha;

    public Pilha() {
        this(CAPACIDADE_PILHA);
    }

    public Pilha(int capacidade){
        this.pilha = (T[]) new Object[capacidade];
        contador = 0;
    }

    public void push(T a){
        if(isFull()){
            throw new IllegalArgumentException("The stack is full");
        }
        pilha[contador] = a;
        contador++;
    }

    public T pop(){
        if(isEmpty()){
            throw new IllegalArgumentException("The stack is empty!");
        }
        contador--;
        T topo = pilha[contador];
        pilha[contador] = null;
        return topo;
    }

    public T top(){
        if(isEmpty()){
            throw new IllegalArgumentException("The stack is empty!");
        }
        return pilha[contador-1];
    }

    public int count(){
        return contador;
    }

    public boolean isFull(){
        return contador == pilha.length;
    }

    public boolean isEmpty(){
        return contador == 0;
    }

    public void clear(){
        while(!isEmpty()){
            pop();
        }
    }

    public int size(){
        return pilha.length;
    }
}