package com.AATRee;

/**
 * Clase para Arbol AA
 */
public class AATRee {

    private AANode root;

    private static AANode nil = new AANode();

    /** Constructor **/
    public AATRee(){
        root = nil;
    }

    /**
     * Verifica si el arbol está vacío
     * @return
     */
    public boolean isEmpty(){
        return this.root == nil;
    }

    /**
     * Vacía el arbol
     */
    public void clear(){
        this.root = nil;
    }

    /**
     * Inserta un elemento con valor x entero
     * @param X
     */
    public void insert(int X){
        root = insert(X, root);
    }

    /**
     * Inserta un valor x en el arbol
     * @param X
     * @param T
     * @return
     */
    private AANode insert(int X, AANode T){
        if (T == nil)
            T = new AANode(X, nil, nil);
        else if (X < T.element)
            T.left = insert(X, T.left);
        else if (X > T.element)
            T.right = insert(X, T.right);
        else
            return T;

        T = skew(T);
        T= split(T);
        return T;
    }

    private AANode skew(AANode T){
        if (T == nil)
            return nil;
        else if (T.left == nil)
            return T;
        else if (T.left.level == T.level){
            AANode L = T.left;

            T.left = L.right;
            L.right = T;
            return L;
        } else {
            return T;
        }
    }

    private AANode split(AANode T){
        if (T == nil){
            return nil;
        }

        else if(T.level == T.right.right.level){
            AANode R = T.right;
            T.right = R.left;
            R.left = T;

            R.level = R.level + 1;
            return R;
        } else
            return T;
    }

    /**
     * Busca un nodo que contenga un valor. Si lo encuentra lo retorna
     * @param val
     * @return
     */
    public AANode search(int val){
        return search(this.root, val);
    }

    private AANode search(AANode r, int val){
        {
            boolean found = false;


            while ((r != nil) && !found){
                int rval = r.element;
                if (val<rval)
                    r = r.left;
                else if (val > rval)
                    r = r.right;
                else if (val == rval){
                    found = true;
                }
            }

            if (found)
                return r;
            else
                return nil;

        }
    }

}
