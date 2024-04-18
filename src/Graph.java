import java.util.*;
import java.util.function.Function;

public class Graph {
    private Map<Integer, List<Integer>> dostList;
    private Map<Integer, Map<Integer, Integer>> wagiKrawedzi;
    private Map<Integer, Integer> wartosciWierzcholkow;

    public Graph(int wierzcholek) {
        dostList = new HashMap<>();
        wagiKrawedzi = new HashMap<>();
        wartosciWierzcholkow = new HashMap<>();
        for (int i = 0; i < wierzcholek; i++) {
            dostList.put(i, new LinkedList<>());
            wagiKrawedzi.put(i, new HashMap<>());
        }
    }

    public Graph(int wierzcholek, Map<Integer, Integer> wartosci) {
        this(wierzcholek);
        for (Map.Entry<Integer, Integer> entry : wartosci.entrySet()) {
            dodajPunkt(entry.getKey(), entry.getValue());
        }
    }

    public void dodajKrawedz(int start, int koniec, int waga) {
        dostList.get(start).add(koniec);
        dostList.get(koniec).add(start);
        wagiKrawedzi.get(start).put(koniec, waga);
        wagiKrawedzi.get(koniec).put(start, waga);
    }

    public void dodajPunkt(int wartosc) {
        int nowyWierzcholek = dostList.size();
        dostList.put(nowyWierzcholek, new LinkedList<>());
        wagiKrawedzi.put(nowyWierzcholek, new HashMap<>());
        wartosciWierzcholkow.put(nowyWierzcholek, wartosc);
    }

    public void dodajPunkt(int wartosc, int waga) {
        dodajPunkt(wartosc);
    }

    public void usunKrawedz(int start, int koniec) {
        dostList.get(start).remove(Integer.valueOf(koniec));
        dostList.get(koniec).remove(Integer.valueOf(start));
        wagiKrawedzi.get(start).remove(koniec);
        wagiKrawedzi.get(koniec).remove(start);
    }

    public void usunPunktIKrawedzie(int wartosc) {
        dostList.remove(wartosc);
        wagiKrawedzi.remove(wartosc);
        wartosciWierzcholkow.remove(wartosc);
        for (List<Integer> listaSasiadow : dostList.values()) {
            listaSasiadow.remove(Integer.valueOf(wartosc));
        }
    }

    public void wyswietlGraf(Map<Integer, Integer> odleglosci) {
        for (Integer wierzcholek : dostList.keySet()) {
            System.out.print("Wierzchołek " + wierzcholek);
            List<Integer> listaSasiadow = dostList.get(wierzcholek);
            if (!listaSasiadow.isEmpty()) {
                System.out.print(" sąsiaduje z:");
                for (int i = 0; i < listaSasiadow.size(); i++) {
                    int sasiad = listaSasiadow.get(i);
                    System.out.print(" " + sasiad + " (" + wagiKrawedzi.get(wierzcholek).get(sasiad) + ")");
                    if (i < listaSasiadow.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(", Najkrótsza odległość od startowego wierzchołka: " + odleglosci.get(wierzcholek));
            } else {
                System.out.println(" nie ma sąsiadów");
            }
        }
    }

    public Map<Integer, Integer> pobierzWartosciWierzcholkow() {
        System.out.println("Wartości wierzchołków: " + wartosciWierzcholkow);
        return wartosciWierzcholkow;
    }

    public Map<Integer, Integer> dijkstra(int start) {
        Map<Integer, Integer> odleglosci = new HashMap<>();
        for (int wierzcholek : dostList.keySet()) {
            odleglosci.put(wierzcholek, Integer.MAX_VALUE);
        }
        odleglosci.put(start, 0);

        PriorityQueue<Integer> kolejka = new PriorityQueue<>((v1, v2) -> odleglosci.get(v1) - odleglosci.get(v2));
        kolejka.offer(start);

        while (!kolejka.isEmpty()) {
            int aktualny = kolejka.poll();
            for (int sasiad : dostList.get(aktualny)) {
                int nowaOdleglosc = odleglosci.get(aktualny) + wagiKrawedzi.get(aktualny).get(sasiad);
                if (nowaOdleglosc < odleglosci.get(sasiad)) {
                    odleglosci.put(sasiad, nowaOdleglosc);
                    kolejka.offer(sasiad);
                }
            }
        }

        return odleglosci;
    }
    public List<int[]> minimalneDrzewoRozpinajace() {
        List<int[]> mst = new ArrayList<>();
        PriorityQueue<int[]> krawedzie = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);

        // Dodaj wszystkie krawędzie do kolejki priorytetowej
        for (int wierzcholek : dostList.keySet()) {
            for (int sasiad : dostList.get(wierzcholek)) {
                int waga = wagiKrawedzi.get(wierzcholek).get(sasiad);
                krawedzie.offer(new int[]{wierzcholek, sasiad, waga});
            }
        }

        // Inicjalizuj tablicę parent
        int[] parent = new int[dostList.size()];
        for (int i = 0; i < dostList.size(); i++) {
            parent[i] = i;
        }

        // Funkcja znajdująca korzeń drzewa
        // Służy do sprawdzania cykli
        Function<Integer, Integer> find = vertex -> {
            while (vertex != parent[vertex]) {
                parent[vertex] = parent[parent[vertex]]; // Path compression
                vertex = parent[vertex];
            }
            return vertex;
        };

        // Rozpocznij algorytm Kruskala
        while (!krawedzie.isEmpty()) {
            int[] krawedz = krawedzie.poll();
            int korzenPoczatku = find.apply(krawedz[0]);
            int korzenKonca = find.apply(krawedz[1]);

            // Jeśli dodanie krawędzi nie spowoduje utworzenia cyklu, dodaj ją do MST
            if (korzenPoczatku != korzenKonca) {
                mst.add(krawedz);
                parent[korzenPoczatku] = korzenKonca; // Union
            }
        }

        return mst;
    }

}
