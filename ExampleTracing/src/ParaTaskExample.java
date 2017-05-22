import pu.pi.ParIteratorFactory;//####[1]####
import java.util.concurrent.ExecutionException;//####[3]####
import java.util.concurrent.atomic.AtomicInteger;//####[4]####
import pt.runtime.CurrentTask;//####[6]####
import pt.runtime.TaskID;//####[7]####
import java.util.*;//####[9]####
//####[9]####
//-- ParaTask related imports//####[9]####
import pt.runtime.*;//####[9]####
import java.util.concurrent.ExecutionException;//####[9]####
import java.util.concurrent.locks.*;//####[9]####
import java.lang.reflect.*;//####[9]####
import pt.runtime.GuiThread;//####[9]####
import java.util.concurrent.BlockingQueue;//####[9]####
import java.util.ArrayList;//####[9]####
import java.util.List;//####[9]####
//####[9]####
public class ParaTaskExample {//####[12]####
//####[12]####
    /*  ParaTask helper method to access private/protected slots *///####[12]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[12]####
        if (m.getParameterTypes().length == 0)//####[12]####
            m.invoke(instance);//####[12]####
        else if ((m.getParameterTypes().length == 1))//####[12]####
            m.invoke(instance, arg);//####[12]####
        else //####[12]####
            m.invoke(instance, arg, interResult);//####[12]####
    }//####[12]####
//####[13]####
    final int NUM_OF_ARRAYS = 10;//####[13]####
//####[14]####
    AtomicInteger working = new AtomicInteger(0);//####[14]####
//####[17]####
    private int[] numbers;//####[17]####
//####[18]####
    private int[] helper;//####[18]####
//####[19]####
    private int number;//####[19]####
//####[21]####
    public void sort(int[] values) {//####[21]####
        this.numbers = values;//####[22]####
        number = values.length;//####[23]####
        this.helper = new int[number];//####[24]####
        mergesort(0, number - 1);//####[25]####
    }//####[26]####
//####[28]####
    private void mergesort(int low, int high) {//####[28]####
        if (low < high) //####[29]####
        {//####[29]####
            int middle = low + (high - low) / 2;//####[30]####
            mergesort(low, middle);//####[31]####
            mergesort(middle + 1, high);//####[32]####
            merge(low, middle, high);//####[33]####
        }//####[34]####
    }//####[35]####
//####[37]####
    private void merge(int low, int middle, int high) {//####[37]####
        for (int i = low; i <= high; i++) //####[39]####
        {//####[39]####
            helper[i] = numbers[i];//####[40]####
        }//####[41]####
        int i = low;//####[43]####
        int j = middle + 1;//####[44]####
        int k = low;//####[45]####
        while (i <= middle && j <= high) //####[46]####
        {//####[46]####
            if (helper[i] <= helper[j]) //####[47]####
            {//####[47]####
                numbers[k] = helper[i];//####[48]####
                i++;//####[49]####
            } else {//####[50]####
                numbers[k] = helper[j];//####[51]####
                j++;//####[52]####
            }//####[53]####
            k++;//####[54]####
        }//####[55]####
        while (i <= middle) //####[56]####
        {//####[56]####
            numbers[k] = helper[i];//####[57]####
            k++;//####[58]####
            i++;//####[59]####
        }//####[60]####
    }//####[62]####
//####[64]####
    private ArrayList<int[]> createArrays(int NUM_OF_ARRAYS) {//####[64]####
        ArrayList<int[]> intArrays = new ArrayList<int[]>();//####[65]####
        System.out.println("Initializing arrays..");//####[66]####
        for (int i = 0; i < NUM_OF_ARRAYS; i++) //####[68]####
        {//####[68]####
            int[] array = new int[70000000];//####[69]####
            for (int j = 0; j < array.length; j++) //####[70]####
            {//####[70]####
                array[j] = (int) Math.random() * 5000;//####[71]####
            }//####[72]####
            intArrays.add(array);//####[73]####
            System.out.println("Created array " + i);//####[74]####
        }//####[75]####
        return intArrays;//####[76]####
    }//####[77]####
//####[80]####
    private static volatile Method __pt__paraTask_IteratorintAr_method = null;//####[80]####
    private synchronized static void __pt__paraTask_IteratorintAr_ensureMethodVarSet() {//####[80]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[80]####
            try {//####[80]####
                __pt__paraTask_IteratorintAr_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTask", new Class[] {//####[80]####
                    Iterator.class//####[80]####
                });//####[80]####
            } catch (Exception e) {//####[80]####
                e.printStackTrace();//####[80]####
            }//####[80]####
        }//####[80]####
    }//####[80]####
    public TaskIDGroup<Void> paraTask(Iterator<int[]> it) {//####[80]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[80]####
        return paraTask(it, new TaskInfo());//####[80]####
    }//####[80]####
    public TaskIDGroup<Void> paraTask(Iterator<int[]> it, TaskInfo taskinfo) {//####[80]####
        // ensure Method variable is set//####[80]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[80]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[80]####
        }//####[80]####
        taskinfo.setParameters(it);//####[80]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[80]####
        taskinfo.setInstance(this);//####[80]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[80]####
    }//####[80]####
    public TaskIDGroup<Void> paraTask(TaskID<Iterator<int[]>> it) {//####[80]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[80]####
        return paraTask(it, new TaskInfo());//####[80]####
    }//####[80]####
    public TaskIDGroup<Void> paraTask(TaskID<Iterator<int[]>> it, TaskInfo taskinfo) {//####[80]####
        // ensure Method variable is set//####[80]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[80]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[80]####
        }//####[80]####
        taskinfo.setTaskIdArgIndexes(0);//####[80]####
        taskinfo.addDependsOn(it);//####[80]####
        taskinfo.setParameters(it);//####[80]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[80]####
        taskinfo.setInstance(this);//####[80]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[80]####
    }//####[80]####
    public TaskIDGroup<Void> paraTask(BlockingQueue<Iterator<int[]>> it) {//####[80]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[80]####
        return paraTask(it, new TaskInfo());//####[80]####
    }//####[80]####
    public TaskIDGroup<Void> paraTask(BlockingQueue<Iterator<int[]>> it, TaskInfo taskinfo) {//####[80]####
        // ensure Method variable is set//####[80]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[80]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[80]####
        }//####[80]####
        taskinfo.setQueueArgIndexes(0);//####[80]####
        taskinfo.setIsPipeline(true);//####[80]####
        taskinfo.setParameters(it);//####[80]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[80]####
        taskinfo.setInstance(this);//####[80]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[80]####
    }//####[80]####
    public void __pt__paraTask(Iterator<int[]> it) {//####[80]####
        System.out.println("I am task number " + CurrentTask.relativeID() + ", being sorted by thread number: " + CurrentTask.currentThreadID());//####[81]####
        while (it.hasNext()) //####[82]####
        {//####[82]####
            sort(it.next());//####[83]####
        }//####[85]####
    }//####[86]####
//####[86]####
//####[88]####
    private void start() {//####[88]####
        ArrayList<int[]> intArrays = createArrays(NUM_OF_ARRAYS);//####[90]####
        System.out.println("There are " + intArrays.size() + " arrays to be sorted.");//####[91]####
        Iterator<int[]> parIterator = ParIteratorFactory.createParIterator(intArrays, NUM_OF_ARRAYS);//####[92]####
        long startTime = System.currentTimeMillis();//####[94]####
        TaskID<Void> id = paraTask(parIterator);//####[95]####
        try {//####[96]####
            id.waitTillFinished();//####[97]####
        } catch (ExecutionException e) {//####[98]####
            e.printStackTrace();//####[99]####
        } catch (InterruptedException e) {//####[100]####
            e.printStackTrace();//####[101]####
        }//####[102]####
        long endTime = System.currentTimeMillis();//####[103]####
        System.out.println("Operation took: " + (endTime - startTime) / 1000.0 + " seconds");//####[104]####
    }//####[105]####
//####[107]####
    public static void main(String[] args) {//####[107]####
        ParaTaskExample program = new ParaTaskExample();//####[108]####
        program.start();//####[109]####
    }//####[110]####
}//####[110]####
