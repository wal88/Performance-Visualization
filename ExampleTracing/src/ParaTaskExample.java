import pu.pi.ParIteratorFactory;//####[1]####
import java.util.concurrent.ExecutionException;//####[3]####
import java.util.concurrent.atomic.AtomicInteger;//####[4]####
import pt.runtime.CurrentTask;//####[6]####
import pt.runtime.TaskID;//####[7]####
import java.util.*;//####[9]####
import utils.MergeSort;//####[10]####
//####[10]####
//-- ParaTask related imports//####[10]####
import pt.runtime.*;//####[10]####
import java.util.concurrent.ExecutionException;//####[10]####
import java.util.concurrent.locks.*;//####[10]####
import java.lang.reflect.*;//####[10]####
import pt.runtime.GuiThread;//####[10]####
import java.util.concurrent.BlockingQueue;//####[10]####
import java.util.ArrayList;//####[10]####
import java.util.List;//####[10]####
//####[10]####
public class ParaTaskExample {//####[12]####
    static{ParaTask.init();}//####[12]####
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
    final int NUM_OF_ARRAYS = 8;//####[13]####
//####[14]####
    final int LENGTH_OF_ARRAY = 4000000;//####[14]####
//####[15]####
    private int[] numbers;//####[15]####
//####[16]####
    private int[] helper;//####[16]####
//####[17]####
    private int number;//####[17]####
//####[19]####
    MergeSort ms = new MergeSort(numbers, helper, number);//####[19]####
//####[20]####
    private ArrayList<int[]> createArrays(int numbersOfArray, int lengthOfArray) {//####[20]####
        ArrayList<int[]> intArrays = new ArrayList<int[]>();//####[21]####
        System.out.println("Initializing arrays..");//####[22]####
        double range = 4294967296.0;//####[24]####
        int min = -2147483648;//####[25]####
        for (int i = 0; i < numbersOfArray; i++) //####[26]####
        {//####[26]####
            int[] array = new int[lengthOfArray];//####[27]####
            for (int j = 0; j < array.length; j++) //####[28]####
            {//####[28]####
                array[j] = (int) (Math.random() * range) + min;//####[29]####
            }//####[30]####
            intArrays.add(array);//####[31]####
            System.out.println("Created array " + i);//####[32]####
        }//####[33]####
        return intArrays;//####[34]####
    }//####[35]####
//####[38]####
    private static volatile Method __pt__paraTask_IteratorintAr_method = null;//####[38]####
    private synchronized static void __pt__paraTask_IteratorintAr_ensureMethodVarSet() {//####[38]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[38]####
            try {//####[38]####
                __pt__paraTask_IteratorintAr_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTask", new Class[] {//####[38]####
                    Iterator.class//####[38]####
                });//####[38]####
            } catch (Exception e) {//####[38]####
                e.printStackTrace();//####[38]####
            }//####[38]####
        }//####[38]####
    }//####[38]####
    public TaskIDGroup<Void> paraTask(Iterator<int[]> it) {//####[38]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[38]####
        return paraTask(it, new TaskInfo());//####[38]####
    }//####[38]####
    public TaskIDGroup<Void> paraTask(Iterator<int[]> it, TaskInfo taskinfo) {//####[38]####
        // ensure Method variable is set//####[38]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[38]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[38]####
        }//####[38]####
        taskinfo.setParameters(it);//####[38]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[38]####
        taskinfo.setInstance(this);//####[38]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[38]####
    }//####[38]####
    public TaskIDGroup<Void> paraTask(TaskID<Iterator<int[]>> it) {//####[38]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[38]####
        return paraTask(it, new TaskInfo());//####[38]####
    }//####[38]####
    public TaskIDGroup<Void> paraTask(TaskID<Iterator<int[]>> it, TaskInfo taskinfo) {//####[38]####
        // ensure Method variable is set//####[38]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[38]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[38]####
        }//####[38]####
        taskinfo.setTaskIdArgIndexes(0);//####[38]####
        taskinfo.addDependsOn(it);//####[38]####
        taskinfo.setParameters(it);//####[38]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[38]####
        taskinfo.setInstance(this);//####[38]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[38]####
    }//####[38]####
    public TaskIDGroup<Void> paraTask(BlockingQueue<Iterator<int[]>> it) {//####[38]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[38]####
        return paraTask(it, new TaskInfo());//####[38]####
    }//####[38]####
    public TaskIDGroup<Void> paraTask(BlockingQueue<Iterator<int[]>> it, TaskInfo taskinfo) {//####[38]####
        // ensure Method variable is set//####[38]####
        if (__pt__paraTask_IteratorintAr_method == null) {//####[38]####
            __pt__paraTask_IteratorintAr_ensureMethodVarSet();//####[38]####
        }//####[38]####
        taskinfo.setQueueArgIndexes(0);//####[38]####
        taskinfo.setIsPipeline(true);//####[38]####
        taskinfo.setParameters(it);//####[38]####
        taskinfo.setMethod(__pt__paraTask_IteratorintAr_method);//####[38]####
        taskinfo.setInstance(this);//####[38]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[38]####
    }//####[38]####
    public void __pt__paraTask(Iterator<int[]> it) {//####[38]####
        System.out.println("I am task number " + CurrentTask.relativeID() + ", being sorted by thread number: " + CurrentTask.currentThreadID());//####[39]####
        while (it.hasNext()) //####[40]####
        {//####[40]####
            ms.sort(it.next());//####[41]####
        }//####[43]####
    }//####[44]####
//####[44]####
//####[46]####
    private void start() {//####[46]####
        ArrayList<int[]> intArrays = createArrays(NUM_OF_ARRAYS, LENGTH_OF_ARRAY);//####[48]####
        Iterator<int[]> parIterator = ParIteratorFactory.createParIterator(intArrays, NUM_OF_ARRAYS);//####[49]####
        System.out.println("There are " + intArrays.size() + " arrays to be sorted. Timer started.");//####[51]####
        long startTime = System.currentTimeMillis();//####[53]####
        TaskID<Void> id = paraTask(parIterator);//####[54]####
        try {//####[55]####
            id.waitTillFinished();//####[56]####
        } catch (ExecutionException e) {//####[57]####
            e.printStackTrace();//####[58]####
        } catch (InterruptedException e) {//####[59]####
            e.printStackTrace();//####[60]####
        }//####[61]####
        long endTime = System.currentTimeMillis();//####[62]####
        System.out.println("Operation took: " + (endTime - startTime) / 1000.0 + " seconds");//####[63]####
    }//####[64]####
//####[66]####
    public static void main(String[] args) {//####[66]####
        ParaTaskExample program = new ParaTaskExample();//####[67]####
        program.start();//####[68]####
    }//####[69]####
}//####[69]####
