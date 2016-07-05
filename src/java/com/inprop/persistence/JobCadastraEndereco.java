/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import java.io.File;

/**
 *
 * @author Ramon
 */
public class JobCadastraEndereco extends Dao {

    public char[] arrChar ={'l','r','z' ,'k','m', 'n','o','e','j','b','p','w','y', 'q','s','t','u','v','x' ,'c','d','h','g','f','i','a'};

//    public void print(char s){
//        System.out.println(s);
//    }
//
    public void print(String s){
        System.out.println(s);
    }


   public String toString(char[] a){
       String s="[";
       for(int i =0; i< a.length; i++){
            s +=a[i]+", ";
       }
       return s+"]";
   }

   public void ordenaASC(char[] a){
       char aux;
       for(int i=0;i < a.length   ; i++){
           for(int j=0; j< a.length ; j++){
               if(  a[i] < a[j]  ){
                   aux    =   a[i];
                   a[i]   =   a[j];
                   a[j]   =   aux;
                }

           }
          
       }
         print("ASC :"+ toString(a));
    }
   public void ordenaDESC(char[] a){
       char aux;
       for(int i=0;i < a.length   ; i++){
           for(int j=0; j< a.length ; j++){
               if(  a[i] > a[j]  ){
                   aux    =   a[i];
                   a[i]   =   a[j];
                   a[j]   =   aux;
                }

           }
            

       }
       print("desc :"+toString(a));
   }

   public static void main(String args[]){
       JobCadastraEndereco a = new JobCadastraEndereco();
       a.ordenaASC(a.arrChar);
       a.ordenaDESC(a.arrChar);

       //        File f = new File("c://");

//        String c[] = f.list();
//        for(String a:c){
//           System.out.println(a.length());
//        }
  }
}

class GameEntry{
    protected String nome;
	protected int    score;

	public GameEntry(){}
	public GameEntry(String n, int s){
	    this.nome  = n;
	    this.score = s;
	}
	public String getNome(){return nome;}
	public void   setNome(String n){this.nome=n;}
	public int    getScore(){return score;}
	public void   setScore(int s){this.score=s;}
	public String toString(){
	    return "{" + nome + ", " + score + "}";
	}
}

class Score{
    public static final int maxEntries = 10;
	protected int numEntries;
	protected GameEntry[] entries;

	public Score(){

        entries    = new GameEntry[maxEntries];
		numEntries = 0;

	}
    public String toString(){

	    String s= "[";
		       for( int i = 0; i<numEntries ; i++ ){
			       if( i > 0 )s+=", ";
			       s += entries[i];
			   }
	     return s+"]";
	}



	public void add(GameEntry g){
            int novoScore = g.getScore();

            if(numEntries == maxEntries){
                if(novoScore <= entries[numEntries-1].getScore()){
                    return ;
                }
            }else{
                numEntries++;
                for(int i = numEntries-1 ; (i>=1)&&novoScore > entries[i+1].getScore(); i-- ){
                    entries[i] = entries[i-1];
                    entries[i] = g;
                }
            }

        }
        public GameEntry remove(int i)throws IndexOutOfBoundsException{
            if((i<0)|| (i> numEntries)){
                throw new IndexOutOfBoundsException("index Invalido : " + i);
            }
            GameEntry temp = entries[i];
            for(int j = i; j<numEntries-1;j++){
               entries[j]= entries[j+1];
               entries[numEntries-1] =null;
               numEntries--;
            }
               return temp;
            }
     }