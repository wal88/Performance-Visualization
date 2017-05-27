import pu.pi.ParIteratorFactory;//####[1]####
import java.util.concurrent.ExecutionException;//####[2]####
import java.util.concurrent.atomic.AtomicInteger;//####[3]####
import pt.runtime.CurrentTask;//####[5]####
import pt.runtime.TaskID;//####[6]####
import java.util.*;//####[8]####
import java.util.ArrayList;//####[9]####
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
public class ParaTaskWithDependency {//####[12]####
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
    final int NUM_OF_ARRAYS = 4;//####[13]####
//####[14]####
    final int LENGTH_OF_ARRAYS = 4000000;//####[14]####
//####[15]####
    private int[] numbers;//####[15]####
//####[16]####
    private int[] helper;//####[16]####
//####[17]####
    private int number;//####[17]####
//####[19]####
    MergeSort ms = new MergeSort(numbers, helper, number);//####[19]####
//####[22]####
    private ArrayList<int[]> createArrays(int numberOfArray, int lengthOfArray) {//####[22]####
        ArrayList<int[]> intArrays = new ArrayList<int[]>();//####[23]####
        System.out.println("Initializing arrays..");//####[24]####
        double range = 4294967296.0;//####[26]####
        int min = -2147483648;//####[27]####
        for (int i = 0; i < numberOfArray; i++) //####[28]####
        {//####[28]####
            int[] array = new int[lengthOfArray];//####[29]####
            for (int j = 0; j < array.length; j++) //####[30]####
            {//####[30]####
                array[j] = (int) (Math.random() * range) + min;//####[31]####
            }//####[32]####
            intArrays.add(array);//####[33]####
            System.out.println("Created array " + i);//####[34]####
        }//####[35]####
        return intArrays;//####[36]####
    }//####[37]####
//####[39]####
    private static volatile Method __pt__paraTask_intAr_method = null;//####[39]####
    private synchronized static void __pt__paraTask_intAr_ensureMethodVarSet() {//####[39]####
        if (__pt__paraTask_intAr_method == null) {//####[39]####
            try {//####[39]####
                __pt__paraTask_intAr_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTask", new Class[] {//####[39]####
                    int[].class//####[39]####
                });//####[39]####
            } catch (Exception e) {//####[39]####
                e.printStackTrace();//####[39]####
            }//####[39]####
        }//####[39]####
    }//####[39]####
    public TaskID<Void> paraTask(int[] array) {//####[39]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[39]####
        return paraTask(array, new TaskInfo());//####[39]####
    }//####[39]####
    public TaskID<Void> paraTask(int[] array, TaskInfo taskinfo) {//####[39]####
        // ensure Method variable is set//####[39]####
        if (__pt__paraTask_intAr_method == null) {//####[39]####
            __pt__paraTask_intAr_ensureMethodVarSet();//####[39]####
        }//####[39]####
        taskinfo.setParameters(array);//####[39]####
        taskinfo.setMethod(__pt__paraTask_intAr_method);//####[39]####
        taskinfo.setInstance(this);//####[39]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[39]####
    }//####[39]####
    public TaskID<Void> paraTask(TaskID<int[]> array) {//####[39]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[39]####
        return paraTask(array, new TaskInfo());//####[39]####
    }//####[39]####
    public TaskID<Void> paraTask(TaskID<int[]> array, TaskInfo taskinfo) {//####[39]####
        // ensure Method variable is set//####[39]####
        if (__pt__paraTask_intAr_method == null) {//####[39]####
            __pt__paraTask_intAr_ensureMethodVarSet();//####[39]####
        }//####[39]####
        taskinfo.setTaskIdArgIndexes(0);//####[39]####
        taskinfo.addDependsOn(array);//####[39]####
        taskinfo.setParameters(array);//####[39]####
        taskinfo.setMethod(__pt__paraTask_intAr_method);//####[39]####
        taskinfo.setInstance(this);//####[39]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[39]####
    }//####[39]####
    public TaskID<Void> paraTask(BlockingQueue<int[]> array) {//####[39]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[39]####
        return paraTask(array, new TaskInfo());//####[39]####
    }//####[39]####
    public TaskID<Void> paraTask(BlockingQueue<int[]> array, TaskInfo taskinfo) {//####[39]####
        // ensure Method variable is set//####[39]####
        if (__pt__paraTask_intAr_method == null) {//####[39]####
            __pt__paraTask_intAr_ensureMethodVarSet();//####[39]####
        }//####[39]####
        taskinfo.setQueueArgIndexes(0);//####[39]####
        taskinfo.setIsPipeline(true);//####[39]####
        taskinfo.setParameters(array);//####[39]####
        taskinfo.setMethod(__pt__paraTask_intAr_method);//####[39]####
        taskinfo.setInstance(this);//####[39]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[39]####
    }//####[39]####
    public void __pt__paraTask(int[] array) {//####[39]####
        ms.sort(array);//####[40]####
        System.out.println("Array is sorted by thread number: " + CurrentTask.currentThreadID());//####[41]####
    }//####[42]####
//####[42]####
//####[44]####
    private void start() {//####[44]####
        List<int[]> intArrays = createArrays(NUM_OF_ARRAYS, LENGTH_OF_ARRAYS);//####[46]####
        System.out.println("There are " + intArrays.size() + " arrays to be sorted. Timer started.");//####[49]####
        long startTime = System.currentTimeMillis();//####[51]####
        TaskID<Void> id1 = paraTask(intArrays.get(0));//####[52]####
        TaskInfo __pt__id2 = new TaskInfo();//####[53]####
//####[53]####
        /*  -- ParaTask dependsOn clause for 'id2' -- *///####[53]####
        __pt__id2.addDependsOn(id1);//####[53]####
//####[53]####
        TaskID<Void> id2 = paraTask(intArrays.get(1), __pt__id2);//####[53]####
        TaskInfo __pt__id3 = new TaskInfo();//####[54]####
//####[54]####
        /*  -- ParaTask dependsOn clause for 'id3' -- *///####[54]####
        __pt__id3.addDependsOn(id1);//####[54]####
//####[54]####
        TaskID<Void> id3 = paraTask(intArrays.get(2), __pt__id3);//####[54]####
        TaskInfo __pt__id4 = new TaskInfo();//####[55]####
//####[55]####
        /*  -- ParaTask dependsOn clause for 'id4' -- *///####[55]####
        __pt__id4.addDependsOn(id2);//####[55]####
        __pt__id4.addDependsOn(id3);//####[55]####
//####[55]####
        TaskID<Void> id4 = paraTask(intArrays.get(3), __pt__id4);//####[55]####
        try {//####[57]####
            id1.waitTillFinished();//####[58]####
            id2.waitTillFinished();//####[59]####
            id3.waitTillFinished();//####[60]####
            id4.waitTillFinished();//####[61]####
        } catch (ExecutionException e) {//####[62]####
            e.printStackTrace();//####[63]####
        } catch (InterruptedException e) {//####[64]####
            e.printStackTrace();//####[65]####
        }//####[66]####
        long endTime = System.currentTimeMillis();//####[67]####
        System.out.println("Operation took: " + (endTime - startTime) / 1000.0 + " seconds");//####[68]####
    }//####[69]####
//####[71]####
    public static void main(String[] args) {//####[71]####
        ParaTaskWithDependency program = new ParaTaskWithDependency();//####[72]####
        program.start();//####[73]####
    }//####[74]####
}//####[74]####
