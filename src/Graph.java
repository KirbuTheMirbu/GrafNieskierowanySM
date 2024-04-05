import java.util.Random;
import java.util.*;
import java.util.Map;

public class Graph {
    private Map<Integer, List<Integer>> dostList;
    private Map<Integer, Integer> wartosciWierzcholkow;

    public Graph (int wierzcholek) {
        dostList = new HashMap<>();
        wartosciWierzcholkow = new HashMap<>();
        for (int i = 0; i < wierzcholek; i++) {
            dostList.put(i, new LinkedList<>());
        }
    }

    public Graph(int wierzcholek, Map<Integer, Integer> wartosci) {
        this(wierzcholek);
        for (Map.Entry<Integer, Integer> entry : wartosci.entrySet()) {
            dodajPunkt(entry.getKey(), entry.getValue());
        }
    }

    public  void dodajKrawedz (int start, int koniec)
    {
        dostList.get(start).add(koniec);
        dostList.get(koniec).add(start);
    }

    public void dodajPunkt(int wartosc) {
        int nowyWierzcholek = dostList.size();
        dostList.put(nowyWierzcholek, new LinkedList<>());
        wartosciWierzcholkow.put(nowyWierzcholek, wartosc);
    }

    public void dodajPunkt(int pozycja, int wartosc) {
        dostList.put(pozycja, new LinkedList<>());
        wartosciWierzcholkow.put(pozycja, wartosc);
    }

    public void usunKrawedz (int start, int koniec) {
        dostList.get(start).remove(Integer.valueOf(koniec));
        dostList.get(koniec).remove(Integer.valueOf(start));
    }

    public void usunPunktIKrawedzie(int wartosc) {
        dostList.remove(wartosc);
        wartosciWierzcholkow.remove(wartosc);
        List<Integer> klucze = new ArrayList<>(dostList.keySet());
        for (Integer klucz : klucze) {
            dostList.get(klucz).remove(Integer.valueOf(wartosc));
        }
    }

    public void wyswietlGraf() {
        for (Integer wierzcholek : dostList.keySet()) {
            System.out.print("Wierzchołek " + wierzcholek);
            List<Integer> listaSasiadow = dostList.get(wierzcholek);
            if (!listaSasiadow.isEmpty()) {
                System.out.print(" sąsiaduje z:");
                for (Integer sasiad : listaSasiadow) {
                    System.out.print(" " + sasiad + ", ");
                }
            } else {
                System.out.print(" nie ma sąsiadów");
            }
            System.out.println();
        }
    }

    public Map<Integer, Integer> pobierzWartosciWierzcholkow() {
        System.out.println("Wartości wierzchołków: " + wartosciWierzcholkow);
        return wartosciWierzcholkow;
    }
    
}