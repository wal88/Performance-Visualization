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
public class ParaTaskExampleArraysParallelised {//####[11]####
//####[11]####
    /*  ParaTask helper method to access private/protected slots *///####[11]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[11]####
        if (m.getParameterTypes().length == 0)//####[11]####
            m.invoke(instance);//####[11]####
        else if ((m.getParameterTypes().length == 1))//####[11]####
            m.invoke(instance, arg);//####[11]####
        else //####[11]####
            m.invoke(instance, arg, interResult);//####[11]####
    }//####[11]####
//####[12]####
    final int NUM_OF_ARRAYS = 10;//####[12]####
//####[14]####
    ArrayList<int[]> intArrays = new ArrayList<int[]>();//####[14]####
//####[16]####
    double range = 4294967296.0;//####[16]####
//####[17]####
    int min = -2147483648;//####[17]####
//####[20]####
    private int[] numbers;//####[20]####
//####[21]####
    private int[] helper;//####[21]####
//####[22]####
    private int number;//####[22]####
//####[24]####
    public void sort(int[] values) {//####[24]####
        this.numbers = values;//####[25]####
        number = values.length;//####[26]####
        this.helper = new int[number];//####[27]####
        mergesort(0, number - 1);//####[28]####
    }//####[29]####
//####[31]####
    private void mergesort(int low, int high) {//####[31]####
        if (low < high) //####[32]####
        {//####[32]####
            int middle = low + (high - low) / 2;//####[33]####
            mergesort(low, middle);//####[34]####
            mergesort(middle + 1, high);//####[35]####
            merge(low, middle, high);//####[36]####
        }//####[37]####
    }//####[38]####
//####[40]####
    private void merge(int low, int middle, int high) {//####[40]####
        for (int i = low; i <= high; i++) //####[42]####
        {//####[42]####
            helper[i] = numbers[i];//####[43]####
        }//####[44]####
        int i = low;//####[46]####
        int j = middle + 1;//####[47]####
        int k = low;//####[48]####
        while (i <= middle && j <= high) //####[49]####
        {//####[49]####
            if (helper[i] <= helper[j]) //####[50]####
            {//####[50]####
                numbers[k] = helper[i];//####[51]####
                i++;//####[52]####
            } else {//####[53]####
                numbers[k] = helper[j];//####[54]####
                j++;//####[55]####
            }//####[56]####
            k++;//####[57]####
        }//####[58]####
        while (i <= middle) //####[59]####
        {//####[59]####
            numbers[k] = helper[i];//####[60]####
            k++;//####[61]####
            i++;//####[62]####
        }//####[63]####
    }//####[65]####
//####[67]####
    private static volatile Method __pt__paraTaskCreateArray__method = null;//####[67]####
    private synchronized static void __pt__paraTaskCreateArray__ensureMethodVarSet() {//####[67]####
        if (__pt__paraTaskCreateArray__method == null) {//####[67]####
            try {//####[67]####
                __pt__paraTaskCreateArray__method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskCreateArray", new Class[] {//####[67]####
                    //####[67]####
                });//####[67]####
            } catch (Exception e) {//####[67]####
                e.printStackTrace();//####[67]####
            }//####[67]####
        }//####[67]####
    }//####[67]####
    public TaskIDGroup<Void> paraTaskCreateArray() {//####[67]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[67]####
        return paraTaskCreateArray(new TaskInfo());//####[67]####
    }//####[67]####
    public TaskIDGroup<Void> paraTaskCreateArray(TaskInfo taskinfo) {//####[67]####
        // ensure Method variable is set//####[67]####
        if (__pt__paraTaskCreateArray__method == null) {//####[67]####
            __pt__paraTaskCreateArray__ensureMethodVarSet();//####[67]####
        }//####[67]####
        taskinfo.setParameters();//####[67]####
        taskinfo.setMethod(__pt__paraTaskCreateArray__method);//####[67]####
        taskinfo.setInstance(this);//####[67]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[67]####
    }//####[67]####
    public void __pt__paraTaskCreateArray() {//####[67]####
        int[] array = new int[4000000];//####[71]####
        for (int j = 0; j < array.length; j++) //####[72]####
        {//####[72]####
            array[j] = (int) (Math.random() * range) + min;//####[73]####
        }//####[74]####
        intArrays.add(array);//####[75]####
        System.out.println("Created array " + CurrentTask.relativeID());//####[76]####
    }//####[78]####
//####[78]####
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
        while (it.hasNext()) //####[83]####
        {//####[83]####
            sort(it.next());//####[84]####
        }//####[86]####
    }//####[87]####
//####[87]####
//####[89]####
    private void start() {//####[89]####
        System.out.println("Initializing arrays..");//####[91]####
        TaskID<Void> arrayTasks = paraTaskCreateArray();//####[92]####
        try {//####[93]####
            arrayTasks.waitTillFinished();//####[94]####
        } catch (ExecutionException e) {//####[95]####
            e.printStackTrace();//####[96]####
        } catch (InterruptedException e) {//####[97]####
            e.printStackTrace();//####[98]####
        }//####[99]####
        Iterator<int[]> parIterator = ParIteratorFactory.createParIterator(intArrays, 4);//####[101]####
        System.out.println("There are " + intArrays.size() + " arrays to be sorted. Timer started.");//####[103]####
        long startTime = System.currentTimeMillis();//####[105]####
        TaskID<Void> id = paraTask(parIterator);//####[106]####
        try {//####[107]####
            id.waitTillFinished();//####[108]####
        } catch (ExecutionException e) {//####[109]####
            e.printStackTrace();//####[110]####
        } catch (InterruptedException e) {//####[111]####
            e.printStackTrace();//####[112]####
        }//####[113]####
        long endTime = System.currentTimeMillis();//####[114]####
        System.out.println("Operation took: " + (endTime - startTime) / 1000.0 + " seconds");//####[115]####
    }//####[116]####
//####[118]####
    public static void main(String[] args) {//####[118]####
        ParaTaskExampleArraysParallelised program = new ParaTaskExampleArraysParallelised();//####[119]####
        program.start();//####[120]####
    }//####[121]####
}//####[121]####
