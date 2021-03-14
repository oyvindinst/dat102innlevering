package ovelse_5;

import java.util.Random;

public class oppg4 {
	//*Sortering ved innsetting (Insertion sort)
	public static <T extends Comparable<T>> 
						     void sorteringVedInsetting(T[] data) {
		for (int index=1; index<data.length; index++) {
			T nokkel = data[index];
			int p = index;
			while (p > 0 && nokkel.compareTo(data[p-1])<0) {
				data[p]=data[p-1];
				p--;
			}
			data[p]=nokkel;
		}
	}
	//*Utvalgssortering (Selection sort)
	//Samme som Plukksortering som vi så i DAT100
	public static <T extends Comparable<T>>
							 void utvalgsSortering (T[] data) {
		
		int minste;
		T temp;
		for(int neste = 0; neste<data.length - 1; neste++) {
			minste = neste;
			for(int sok = neste+1; sok<data.length; sok++) {
				if(data[sok].compareTo(data[minste])<0) {
					minste = sok;
				}
			}
			temp=data[minste];
			data[minste]=data[neste];
			data[neste]=temp;
		}
	}
	
	//*Boblesortering (Bubble sort)
	public static <T extends Comparable<T>>
							void bobleSort (T[]data) {
		T temp;
		for (int p = data.length -1; p>= 0; p--) {
			for(int sok = 0; sok<=p-1; sok++) {
				if(data[sok].compareTo(data[sok+1])>0) {
					temp = data[sok];
					data[sok]=data[sok + 1];
					data[sok+1]=temp;
				}
			}
		}
	}
	
	//*Kvikksortering (Quick sort)
	private static<T extends Comparable<T>>
	void swap(T[] data, int index1, int index2) {
		T temp = data[index1];
		data[index1]=data[index2];
		data[index2]=temp;
	}
	
	private static <T extends Comparable<T>>
						int finnPartisjon (T[] data, int min, int max) {
		int venstre, hoyre;
		T temp, pivot;
		int midten = (min+max)/2;
		pivot = data[midten];
		swap(data, midten, min);
		
		venstre = min+1;
		hoyre = max;
		while(venstre < hoyre) {
			
			while (venstre < hoyre && 
					data[venstre].compareTo(pivot)<=0) {
				venstre++;
			}
			while (data[hoyre].compareTo(pivot)>0) {
				hoyre--;
			}
			if (venstre < hoyre) {
				swap(data, venstre, hoyre);
			}
		}
		swap(data, min, hoyre);
		return hoyre;
	}
	
	public static <T extends Comparable<T>>
							void kvikkSort (T[] data, int min, int max) {
		int posPartisjon;
		if (min < max) {
			posPartisjon = finnPartisjon(data, min, max);
			
			kvikkSort(data, min, posPartisjon-1);
			kvikkSort(data, posPartisjon+1, max);
		}
	}
	
	//*Flettesortering (Merge sort)
	
	public static <T extends Comparable<T>>
	void flette(T[]tabell, int forste, int midten, int siste) {
		int storrelse=siste-forste+1;
		T[] hjelpeTabell=(T[])(new Comparable[storrelse]);
		
		int forsteV=forste;
		int sisteV=midten;
		int forsteH=midten+1;
		int sisteH=siste;
		
		int h=0;
		
		while((forsteV<=sisteV && forsteH<=sisteH)) {
			if(tabell[forsteV].compareTo(tabell[forsteH])<=0) {
				hjelpeTabell[h]=tabell[forsteV];
				forsteV++;
			}else {
				hjelpeTabell[h]=tabell[forsteH];
				forsteH++;
			}
			h++;
		}
		while(forsteV<=sisteV) {
			hjelpeTabell[h]=tabell[forsteV];
			forsteV++;
			h++;
		}
		while(forsteH<=sisteH) {
			hjelpeTabell[h]=tabell[forsteH];
			forsteH++;
			h++;
		}
		h = 0;
		for (int index=forste; index<=siste; index++) {
			tabell[index]=hjelpeTabell[h];
			h++;
		}
	}
		public static <T extends Comparable<T>>
		void fletteSort(T[]tabell, int forste, int siste) {
			if(forste < siste) {
				int midten=(forste+siste)/2;
				fletteSort(tabell, forste, midten);
				fletteSort(tabell, midten+1, siste);
				flette(tabell, forste, midten, siste);
			}
	}
		
			//b)
		public static <T extends Comparable<T>> void kvikkSortNy(T[] data) {
			kvikkSortNy(data, 0, data.length - 1);
			sorteringVedInsetting(data);
			} 
		
			public static <T extends Comparable<T>> void kvikkSortNy(T[] data,
			 int min, int maks) {
			final int MIN = 10;
			int posPartisjon;
			if (maks - min + 1 > MIN) {//antall elementer > MIN ?
			posPartisjon = finnPartisjon(data, min, maks);
			kvikkSortNy(data, min, posPartisjon - 1);
			kvikkSortNy(data, posPartisjon + 1, maks);
			}
		}
		
	public static void main(String[]args) {
		Integer [] tab = {3, 1, 6, 4, 2, 7, 5,3,7,1,2} ;
		Integer [] tab2 = {1,1,1,1,1,1,1,1,1,1};
		//sorteringVedInsetting(tab);
		//utvalgsSortering(tab);
		//bobleSort(tab);
		//kvikkSort(tab, 0, tab.length-1);
		//fletteSort(tab, 0, tab.length-1);
		kvikkSortNy(tab, 0, tab.length-1);
		
		for(Integer e : tab) {
			System.out.println(e+" ");
		}
		System.out.println();
		
		//c)
		//bobleSort(tab2);
		
		
		//for(Integer e : tab2) {
		//	System.out.println(e+" ");
		//}
		//System.out.println();
		
		Random tilfeldig = new Random();
	int n = 8000;
	int antal = 10;
	Integer[][] a = new Integer[antal][n];
	// set inn tilfeldige heiltal i alle rekker
	for (int i = 0; i < antal; i++) {
		for (int j = 0; j < n; j++) {
	a[i][j] = tilfeldig.nextInt();
		}
	}
	// start tidsmåling
	double startTid = System.nanoTime();
	for (int indeks = 0; indeks < antal; indeks++) {
	//sorteringVedInsetting(a[indeks]);
	//utvalgsSortering(a[indeks]);
	//bobleSort(a[indeks]);
	//kvikkSort(a[indeks], 0, a[indeks].length-1);
	//fletteSort(a[indeks], 0, a[indeks].length-1);
	kvikkSortNy(a[indeks], 0, a[indeks].length-1);
	// blir ein eindimensjonal tabel
	}
	double sluttTid = System.nanoTime();
	double tidBrukt = (sluttTid-startTid)/1000000;
	double snittTid = tidBrukt/antal;
	System.out.println("Starttid: "+startTid);
	System.out.println("Sluttid: "+sluttTid);
	System.out.println("Brukt tid: "+tidBrukt);
	System.out.println("Antall målinger: "+antal);
	System.out.println("Gjennomsnittleg tid brukt: " + snittTid);
			} 
	
	}
