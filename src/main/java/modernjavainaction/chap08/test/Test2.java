package modernjavainaction.chap08.test;

import java.util.*;

import static java.util.Map.entry;

public class Test2 {
    public static void main(String[] args) {
//        collectionFactory();
//        listSetAdvanced();
        mapAdvanceMethod();
    }

    public static void collectionFactory(){
        System.out.println("# list>>");
        List<String> friendsList = Arrays.asList("Raphael", "Olivia", "Thibaut");
        friendsList.set(0, "Richard");
        friendsList.stream().forEach(i-> System.out.println(i));

        System.out.println("# set>>");
        Set<String> friendsSet = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
        friendsSet.stream().forEach(i-> System.out.println(i));

        friendsList = List.of("Raphael", "Olivia", "Thibaut");
//        friendsList.set(0, "Richard"); Err UnsupportedOperationException

        friendsSet = Set.of("Raphael", "Olivia", "Thibaut");
//        중복요소 IllegalArgumentException

//        10개 이하
        Map<String,Integer> firendsMap = Map.of("Raphael",30,"Olivia",25,"Thibaut",26);
//        10개 이상
        firendsMap = Map.ofEntries(
                entry("Rahael",30),
                entry("Olivai",25),
                entry("Thibaut",26)
        );
    }

    public static void listSetAdvanced(){
        List<String> friendsList = new ArrayList<>();
        friendsList.add("A");
        friendsList.add("AB");
        friendsList.add("AC");
        friendsList.add("B");
        friendsList.removeIf(s->s.length()>=2);
        friendsList.sort(Comparator.naturalOrder());
        friendsList.replaceAll(s->s.toUpperCase());

        friendsList.stream().forEach(s-> System.out.println(s));

    }

    public static void mapAdvanceMethod(){
        Map<String,Integer> friendsMap = new HashMap<>();
        friendsMap.put("Rahael", 25);
        friendsMap.put("Oliva", 26);
        friendsMap.put("Thibaut", 30);

        friendsMap.forEach((k,v)-> System.out.println("k:"+k +", v:"+v));
        friendsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        System.out.println("friendsMap.getOrDefault(\"geona\") = " + friendsMap.getOrDefault("geona",31));

        System.out.println(">>computeIfAbsent");
        friendsMap.computeIfAbsent("Geonah", (k)->31);
        friendsMap.entrySet()
                .forEach(System.out::println);

        System.out.println("computeIfPresent");
        friendsMap.computeIfPresent("Rahael",(k,v)->v+k.charAt(0));
        friendsMap.entrySet()
                .forEach(System.out::println);

        System.out.println(">>compute");
        friendsMap.compute("Youchel",(k,v)->31);
        friendsMap.entrySet()
                .forEach(System.out::println);

        System.out.println(">>remove k,v");
        friendsMap.remove("Rahael", 107);
        friendsMap.entrySet()
                .forEach(System.out::println);

        System.out.println(">>replaceAll");
        friendsMap.replaceAll((k,v)->v+k.charAt(0));
        friendsMap.entrySet()
                .forEach(System.out::println);

        System.out.println(">>merge");
        Map<String,Integer> family = Map.ofEntries(
                entry("Cristina", 31),
                entry("Raphael", 31),
                entry("Geonah", 31),
                entry("Test", 100)
        );
        Map<String,Integer> everyone = new HashMap<>(family);
        friendsMap.forEach((k,v)->{
            everyone.merge(k, v, (v1,v2)-> v1+v2);
        });
        everyone.entrySet().stream()
                .forEachOrdered(System.out::println);
    }
}
