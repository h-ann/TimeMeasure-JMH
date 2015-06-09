package srjmh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS) // for buble, selection, merge,quick
//@OutputTimeUnit(TimeUnit.NANOSECONDS) //for insertion, bh,string 
public class func {

    BinaryHeap b;
    BinaryHeap b1;
    int[] val;
    int x;
    String st;

    public int[] setUp(int size, int max) {
        int[] values = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            values[i] = r.nextInt(max);
        }
        return values;
    }

    @Param({"100", "200", "400", "800", "10000", "40000"}) // for buble
    //@Param({"200", "400", "800", "1600", "40000", "160000"})//for insertion,selection
    //@Param({"100", "200", "400", "800", "1600",    "10000", "40000", "160000" })
    //@Param({"400","800", "1600", "3200", "6400", "160000" ,"640000","2560000"}) 
    //@Param({"800", "1600", "3200", "6400", "12800","640000","2560000","10240000"}) // merge,quick,impl
    //@Param({"400","800", "1600", "3200", "6400", "160000" ,"640000","2560000"})// for bh
    //@Param({"400", "800", "1600", "10000", "40000", "160000"}) // for substring
    private int len;

    @Setup
    public void prepare() throws FileNotFoundException {
       System.out.println("setup");
       b = new BinaryHeap(len);
       b.insertall(10000000);
       val = setUp(len, 100);
       st = getStr();
    }
    
    @Benchmark
    public int[] testbuble() {
        buble m = new buble();
        return m.sortBuble(val);
    }
    
    @Benchmark
    public int[] testinsertion() {
        insertion i = new insertion();
        return i.insertionSort(val);
    }
    
    @Benchmark
    public int[] testselection() {
        selection i = new selection();
        return i.SelectionSort(val);
    }
    
    @Benchmark
    public int[] testmerge() {
        merge m = new merge();
        return m.sortMerge(val);
    }
    
    @Benchmark
    public int[] testquick() {
        quick m = new quick();
        return m.sort(val);
    }
    
    @Benchmark
    public int testimplquick() {
        Arrays.sort(val);
        return 0;
    } 
    
    @Benchmark
    public void testheap() {
        b.insert(13);
        b.delete(0);
    }
    public String getStr() throws FileNotFoundException {
        LinkedList str = new LinkedList();
        FileInputStream fis = new FileInputStream("/home/ania/NetBeansProjects/Zaratustra.txt");
        String st = new Scanner(fis, "UTF-8").useDelimiter("\\A").next();
        return st;
    }

    @Benchmark
    public String testSubStr() {
        return st.substring(100, len);
    }

    public static void main(String[] args) throws RunnerException, FileNotFoundException {

        System.setOut(new PrintStream("/home/ania/RESULTS/resBench/res.txt"));
        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(".*" + func.class.getSimpleName() + ".*")
                .warmupIterations(3)
                .measurementIterations(3)
                .forks(1)
                .build();

        new Runner(opt).run();

    }
}
