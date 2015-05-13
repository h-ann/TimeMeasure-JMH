package srjmh;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class func {

    BinaryHeap bh;
    int[] val;
    int x;

    public int[] setUp(int size, int max) {
        int[] values = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            values[i] = r.nextInt(max);
        }
        return values;
    }
//
//    public void print(int[] val) {
//        Vector v = new Vector();
//        for (int i = 0; i < val.length; i++) {
//            v.add(val[i]);
//        }
//        System.out.println(v);
//    }

    //@Param({"100", "200", "400", "800", "10000", "40000" }) // for buble, selection
    //@Param({"200", "400", "800", "1600", "40000", "160000"})//for insertion
    //@Param({"100", "200", "400", "800", "1600",    "10000", "40000", "160000" })
    // @Param({"400","800", "1600", "3200", "6400", "160000" ,"640000","2560000"})
    //  private int len;
//    @Setup
//    public void prepare() {
//        val = setUp(len, 100);
//    }
//    @Benchmark
//    public int[] testmerge() {
//        merge m = new merge();
//        return m.sortMerge(val);
//    }
//    @Benchmark
//    public int[] testquick() {
//        quick m = new quick();
//        return m.sort(val);
//    }
//    @Benchmark
//    public int[] testinsertion() {
//        insertion i = new insertion();
//        return i.insertionSort(val);
//    }  
//    @Benchmark
//    public int[] testselection() {
//        selection i = new selection();
//        return i.SelectionSort(val);
//    }
//   @Benchmark
//    public int[] testbuble() {
//        buble m = new buble();
//        return m.sortBuble(val);
//    }    
//   @Benchmark
//    public int testimplquick() {
//        Arrays.sort(val);
//        return 0;
//    } 
    @Param({"400","800", "1600", "3200", "6400", "160000" ,"640000","2560000"})
    private int len;
    @Setup
    public void prepare() {
        bh = new BinaryHeap(len);
        bh.insertall(100);
    }


    @Benchmark
    public int testheap() {
       // bh = new BinaryHeap(len);
       // bh.insertall(100);
        return bh.delete(0);
    }

    public static void main(String[] args) throws RunnerException, FileNotFoundException {

        //System.setOut(new PrintStream("/home/ania/res.txt"));
        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(".*" + func.class.getSimpleName() + ".*")
                .warmupIterations(3)
                .measurementIterations(3)
                .forks(1)
                .build();

        new Runner(opt).run();

    }
}
