import pu.pi.ParIteratorFactory;//####[1]####
import java.util.concurrent.atomic.AtomicInteger;//####[2]####
import pt.runtime.CurrentTask;//####[4]####
import java.util.*;//####[6]####
//####[6]####
//-- ParaTask related imports//####[6]####
import pt.runtime.*;//####[6]####
import java.util.concurrent.ExecutionException;//####[6]####
import java.util.concurrent.locks.*;//####[6]####
import java.lang.reflect.*;//####[6]####
import pt.runtime.GuiThread;//####[6]####
import java.util.concurrent.BlockingQueue;//####[6]####
import java.util.ArrayList;//####[6]####
import java.util.List;//####[6]####
//####[6]####
public class ParaTaskExample {//####[9]####
    static{ParaTask.init();}//####[9]####
    /*  ParaTask helper method to access private/protected slots *///####[9]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[9]####
        if (m.getParameterTypes().length == 0)//####[9]####
            m.invoke(instance);//####[9]####
        else if ((m.getParameterTypes().length == 1))//####[9]####
            m.invoke(instance, arg);//####[9]####
        else //####[9]####
            m.invoke(instance, arg, interResult);//####[9]####
    }//####[9]####
//####[10]####
    static int numOfArrays = 4;//####[10]####
//####[11]####
    AtomicInteger working = new AtomicInteger(0);//####[11]####
//####[13]####
    private int[] numbers;//####[13]####
//####[14]####
    private int[] helper;//####[14]####
//####[15]####
    private int number;//####[15]####
//####[17]####
    public void sort(int[] values) {//####[17]####
        this.numbers = values;//####[18]####
        number = values.length;//####[19]####
        this.helper = new int[number];//####[20]####
        mergesort(0, number - 1);//####[21]####
    }//####[22]####
//####[24]####
    private void mergesort(int low, int high) {//####[24]####
        if (low < high) //####[25]####
        {//####[25]####
            int middle = low + (high - low) / 2;//####[26]####
            mergesort(low, middle);//####[27]####
            mergesort(middle + 1, high);//####[28]####
            merge(low, middle, high);//####[29]####
        }//####[30]####
    }//####[31]####
//####[33]####
    private void merge(int low, int middle, int high) {//####[33]####
        for (int i = low; i <= high; i++) //####[35]####
        {//####[35]####
            helper[i] = numbers[i];//####[36]####
        }//####[37]####
        int i = low;//####[39]####
        int j = middle + 1;//####[40]####
        int k = low;//####[41]####
        while (i <= middle && j <= high) //####[42]####
        {//####[42]####
            if (helper[i] <= helper[j]) //####[43]####
            {//####[43]####
                numbers[k] = helper[i];//####[44]####
                i++;//####[45]####
            } else {//####[46]####
                numbers[k] = helper[j];//####[47]####
                j++;//####[48]####
            }//####[49]####
            k++;//####[50]####
        }//####[51]####
        while (i <= middle) //####[52]####
        {//####[52]####
            numbers[k] = helper[i];//####[53]####
            k++;//####[54]####
            i++;//####[55]####
        }//####[56]####
    }//####[58]####
//####[60]####
    private ArrayList<int[]> createArrays(int numOfArrays) {//####[60]####
        ArrayList<int[]> intArrays = new ArrayList<int[]>();//####[61]####
        for (int i = 0; i < numOfArrays; i++) //####[63]####
        {//####[63]####
            int[] array = new int[99999999];//####[65]####
            for (int j = 0; j < array.length; j++) //####[66]####
            {//####[66]####
                array[j] = (int) Math.random() * 5000;//####[67]####
            }//####[68]####
            intArrays.add(array);//####[69]####
        }//####[70]####
        return intArrays;//####[71]####
    }//####[72]####
//####[74]####
    private static volatile Method __pt__paraTask_IteratorintAr_method = null;//####[74]####
    private synchronized static void __pt__paraTask_IteratorintAr_ensureMethodVarSet() {//####[74]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[74]####
            try {//####[74]####
                __pt__paraTask_IteratorintAr_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTask", new Class[] {//####[74]####
                    Iterator.class//####[74]####
                });//####[74]####
            } catch (Exception e) {//####[74]####
                e.printStackTrace();//####[74]####
            }//####[74]####
        }//####[74]####
    }//####[74]####
    public TaskIDGroup<Void> paraTask(Iterator<int[]> it) {//####[74]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[74]####
        return paraTask(it, new TaskInfo());//####[74]####
    }//####[74]####
    public TaskIDGroup<Void> paraTask(Iterator<int[]> it, TaskInfo taskinfo) {//####[74]####
        // ensure Method variable is set//####[74]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[74]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[74]####
        }//####[74]####
        taskinfo.setParameters(it);//####[74]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[74]####
        taskinfo.setInstance(this);//####[74]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, 4);//####[74]####
    }//####[74]####
    public TaskIDGroup<Void> paraTask(TaskID<Iterator<int[]>> it) {//####[74]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[74]####
        return paraTask(it, new TaskInfo());//####[74]####
    }//####[74]####
    public TaskIDGroup<Void> paraTask(TaskID<Iterator<int[]>> it, TaskInfo taskinfo) {//####[74]####
        // ensure Method variable is set//####[74]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[74]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[74]####
        }//####[74]####
        taskinfo.setTaskIdArgIndexes(0);//####[74]####
        taskinfo.addDependsOn(it);//####[74]####
        taskinfo.setParameters(it);//####[74]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[74]####
        taskinfo.setInstance(this);//####[74]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, 4);//####[74]####
    }//####[74]####
    public TaskIDGroup<Void> paraTask(BlockingQueue<Iterator<int[]>> it) {//####[74]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[74]####
        return paraTask(it, new TaskInfo());//####[74]####
    }//####[74]####
    public TaskIDGroup<Void> paraTask(BlockingQueue<Iterator<int[]>> it, TaskInfo taskinfo) {//####[74]####
        // ensure Method variable is set//####[74]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[74]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[74]####
        }//####[74]####
        taskinfo.setQueueArgIndexes(0);//####[74]####
        taskinfo.setIsPipeline(true);//####[74]####
        taskinfo.setParameters(it);//####[74]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[74]####
        taskinfo.setInstance(this);//####[74]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, 4);//####[74]####
    }//####[74]####
    public void __pt__paraTask(Iterator<int[]> it) {//####[74]####
        System.out.println("I am task number " + CurrentTask.relativeID());//####[75]####
        System.out.println("I am thread number: " + CurrentTask.currentThreadID());//####[76]####
        while (it.hasNext()) //####[77]####
        {//####[77]####
            sort(it.next());//####[78]####
        }//####[80]####
    }//####[81]####
//####[81]####
//####[83]####
    public static void main(String[] args) {//####[83]####
        System.out.println("Hello");//####[84]####
        ParaTaskExample program = new ParaTaskExample();//####[85]####
        int numOfThreads = 4;//####[86]####
        ArrayList<int[]> intArrays = program.createArrays(numOfArrays);//####[89]####
        System.out.println(intArrays.size());//####[90]####
        Iterator<int[]> parIterator = ParIteratorFactory.createParIterator(intArrays, numOfThreads);//####[91]####
        long start = System.currentTimeMillis();//####[93]####
        program.paraTask(parIterator);//####[94]####
        long end = System.currentTimeMillis();//####[96]####
        System.out.println("Operation took: " + (end - start) / 1000.0 + " seconds");//####[97]####
    }//####[98]####
}//####[98]####
