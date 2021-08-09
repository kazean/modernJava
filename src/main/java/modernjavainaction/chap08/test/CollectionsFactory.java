package modernjavainaction.chap08.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.*;

public class CollectionsFactory {
    public static void main(String[] args) {
        /*
        listFactory();
        setFactory();
        mapFactory();
        listSetFactoryMethod();
        */
        mapFactoryMethod();
    }

    public static void listFactory(){
        List<Integer> list1 = Arrays.asList(1,2,3);
        System.out.println("list1 > " + list1);

        try{
            list1.add(4);
        }catch (UnsupportedOperationException e){
            System.out.println("list1.add() UnsupportedException");
            list1.set(2,4);
            System.out.println(list1);
        }
        System.out.println("");

        List<Integer> list2 = List.of(1,2,3);
        System.out.println("list2 > " + list2);
        try{
            list2.set(2,0);
        }catch (UnsupportedOperationException e){
            System.out.println("list2.set() UnsupportedException");
            System.out.println("list2 fixed");
        }
        System.out.println();
    }

    public static void setFactory(){
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1,2,3));
        System.out.println("set1 > "+ set1);
        System.out.println();

        Set<Integer> set2= Stream.of(1,2,3)
                .collect(Collectors.toSet());
        System.out.println("set2 > "+set2);
        System.out.println();

        Set<Integer> set3 = Set.of(1,2,3);
        System.out.println("set3 > "+set2);
        System.out.println();
    }


    public static void mapFactory(){
        Map<String, Integer> map1 = Map.of("first",1,"second",2,"third",3);
        System.out.println("map1 > "+map1);
        System.out.println();

        Map<String, Integer> map2 = Map.ofEntries(entry("first",1)
                                            ,entry("second",2)
                                            ,entry("third",3));
        System.out.println("map2 > "+map1);
        System.out.println();

    }

    public static void listSetFactoryMethod(){
        List<String> list1 = new ArrayList<>();
        list1.add("b01");
        list1.add("a01");
        list1.add("d01");
        list1.add("c01");
        System.out.println("list1 > " +list1);

        list1.removeIf(code -> code.charAt(0) == 'c');
        System.out.println("list1 > " +list1);
        System.out.println();

        list1.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        System.out.println("list1 > " +list1);
        System.out.println();

        list1.sort(Comparator.naturalOrder());
        System.out.println("list1 > " +list1);
        System.out.println();

        list1.sort(Comparator.reverseOrder());
        System.out.println("list1 > " +list1);
        System.out.println();
    }

    public static void mapFactoryMethod(){
        Map<String,Integer> map = new HashMap<>();
        map.put("gunna",31);
        map.put("jju",34);
        map.put("junea",33);

        System.out.println("natural for");
        Set<Entry<String, Integer>> set = map.entrySet();
        Iterator<Entry<String,Integer>> iter = set.iterator();
        while(iter.hasNext()){
            Entry<String,Integer> entry = iter.next();
            System.out.println(entry.getKey() + ":" +entry.getValue());
        }
        System.out.println();

        System.out.println("enhanced for");
        for(Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey() + ":" +entry.getValue());
        }
        System.out.println();

        System.out.println("forEach");
        map.forEach((k,v) -> System.out.println(k+":"+v));
        System.out.println();

//        Entry.comparingByKey, Entry.comparingByValue()
        Map<String,String> favouriteMovies = Map.ofEntries(entry("Raphael","Star Wars"),
                entry("Cristina","Matrix"),
                entry("Olivia","James Bond"));
        favouriteMovies.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(System.out::println);
        System.out.println();

//        getOrDefault(Object key, Object defaultValue);
        System.out.println(favouriteMovies.getOrDefault("Olivia","Matrix"));
        System.out.println(favouriteMovies.getOrDefault("default","default"));
        System.out.println();

//        default v  computeIfAbsent (K key, Function<K,v> mappingFunction) 값이 null일 경우 새값계산
        Map<String, List<String>> friendsToMovies = new HashMap<>();

        System.out.println("--> Adding a friend and movie in a verbose way");
        String friend = "Raphael";
        List<String> movies = friendsToMovies.get(friend);
        if (movies == null) {
            movies = new ArrayList<>();
            friendsToMovies.put(friend, movies);
        }
        movies.add("Star Wars");
        System.out.println(friendsToMovies);

        System.out.println("--> Adding a friend and movie using computeIfAbsent()");
        friendsToMovies.clear();
        friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>())
                .add("Star Wars");
        List<String> movieTmp = friendsToMovies.computeIfAbsent("gunna", name->new ArrayList<>());
        movieTmp.add("test1");
        movieTmp.add("test2");
        System.out.println(friendsToMovies);
        System.out.println();


//        default V computeIfPresent(K k, BiFunction<K,V,V> mapping) 값이 있는 경우 새값계산
        friendsToMovies.computeIfPresent("gunna",(name,list)-> List.of("test01","test02"));
        System.out.println(friendsToMovies);
        System.out.println();

//        default V compute(K k, BiFunction<K, V, V> remapping) 값이 있으면 old 이용해 새값계산, 없으면 newMapping
        friendsToMovies.compute("gunna2",(name,list)->List.of(name));
        System.out.println(friendsToMovies);
        System.out.println();

        friendsToMovies.compute("gunna",(name,list)->List.of(name));
        System.out.println(friendsToMovies);
        System.out.println();

//        default void replaceAll(BiFuction<K,V,V> mapping)
        Map<String,String> map2 = new HashMap<>();
        map2.put("Raphael","Star Wars");
        map2.put("Olivia","James bond");
        map2.replaceAll((name,val)->val.toUpperCase());
        System.out.println(map2);

//        Merge

//        Basic Merge 중복이 없을 경우 올바르게 동작
        Map<String, String> familyMap = Map.ofEntries(entry("Teo","Starwars"),
                entry("Cristina","James Bond"));
        Map<String, String> friends = Map.ofEntries(entry("Raphael","Star Wars"));
        Map<String, String> everyone = new HashMap<>(familyMap);
        everyone.putAll(friends);
        System.out.println(everyone);
        System.out.println();

//        map.forEach( (k,v) -> everyoneMap.merge(K k,V v,BiFunction<K,V,V> reMappingFunction))
//        default V merge(K k,V v,BiFunction<K,V,V> reMappingFunction)
        familyMap = Map.ofEntries(entry("Teo","Starwars"),
                entry("Cristina","James Bond"));
        friends = Map.ofEntries(entry("Raphael","Star Wars"),
                entry("Cristina","Matrix"));
        HashMap<String,String> everyone2 = new HashMap<>(familyMap);
        friends.forEach((k,v)-> everyone2.merge(k,v,(movie1,movie2)->movie1 +"&"));
        System.out.println(everyone2);
    }

}
