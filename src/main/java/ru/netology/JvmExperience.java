package ru.netology;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JvmExperience {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Please open 'ru.netology.JvmExperience' in VisualVm");
        Thread.sleep(30_000);
        System.out.println("1");
        System.out.println("Started at: "+ LocalTime.now());
        loadToMetaspaceAllFrom("io.vertx");                                //1
        System.out.println("Finished at: "+ LocalTime.now());

        Thread.sleep(3_000);
        System.out.println("2");
        System.out.println("Started at: "+ LocalTime.now());
        loadToMetaspaceAllFrom("io.netty");                                //2
        System.out.println("Finished at: "+ LocalTime.now());

        Thread.sleep(3_000);
        System.out.println("3");
        System.out.println("Started at: "+ LocalTime.now());
        loadToMetaspaceAllFrom("org.springframework");                     //3
        System.out.println("Finished at: "+ LocalTime.now());

        Thread.sleep(3_000);

        System.out.println(LocalTime.now() + ": now see heap");
        System.out.println("4");
        System.out.println("Started at: "+ LocalTime.now());
        List<SimpleObject> simpleObjects = createSimpleObjects(5_000_000);       //4
        System.out.println("Finished at: "+ LocalTime.now());

        Thread.sleep(3_000);
        System.out.println("5");
        System.out.println("Started at: "+ LocalTime.now());
        simpleObjects.addAll(createSimpleObjects(5_000_000));                    //5
        System.out.println("Finished at: "+ LocalTime.now());

        Thread.sleep(3_000);
        System.out.println("6");
        System.out.println("Started at: "+ LocalTime.now());
        simpleObjects.addAll(createSimpleObjects(5_000_000));                    //6
        System.out.println("Finished at: "+ LocalTime.now());
        Thread.sleep(3_000);
    }

    static void loadToMetaspaceAllFrom(String packageName) {
        System.out.println(LocalTime.now() + ": loading " + packageName);

        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);

        System.out.println(LocalTime.now() + ": loaded " + allClasses.size() + " classes");
    }

    private static List<SimpleObject> createSimpleObjects(int count) {
        System.out.println(LocalTime.now() + ": creating " + count + " objects");

        List<SimpleObject> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(new SimpleObject(i));
        }

        System.out.println(LocalTime.now() + ": created");
        return result;
    }

    static class SimpleObject {
        final Integer value;
        SimpleObject(int value) {
            this.value = value;
        }
    }
}
