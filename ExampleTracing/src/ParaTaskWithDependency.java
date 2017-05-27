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
    final int NUM_OF_ARRAYS = 6;//####[13]####
//####[14]####
    final int LENGTH_OF_ARRAYS = 40000000;//####[14]####
//####[15]####
    ArrayList<int[]> intArrays = new ArrayList<int[]>();//####[15]####
//####[16]####
    private int[] numbers;//####[16]####
//####[17]####
    private int[] helper;//####[17]####
//####[18]####
    private int number;//####[18]####
//####[20]####
    MergeSort ms = new MergeSort(numbers, helper, number);//####[20]####
//####[22]####
    private static volatile Method __pt__paraTaskCreateArrays__method = null;//####[22]####
    private synchronized static void __pt__paraTaskCreateArrays__ensureMethodVarSet() {//####[22]####
        if (__pt__paraTaskCreateArrays__method == null) {//####[22]####
            try {//####[22]####
                __pt__paraTaskCreateArrays__method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskCreateArrays", new Class[] {//####[22]####
                    //####[22]####
                });//####[22]####
            } catch (Exception e) {//####[22]####
                e.printStackTrace();//####[22]####
            }//####[22]####
        }//####[22]####
    }//####[22]####
    public TaskIDGroup<Void> paraTaskCreateArrays() {//####[22]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[22]####
        return paraTaskCreateArrays(new TaskInfo());//####[22]####
    }//####[22]####
    public TaskIDGroup<Void> paraTaskCreateArrays(TaskInfo taskinfo) {//####[22]####
        // ensure Method variable is set//####[22]####
        if (__pt__paraTaskCreateArrays__method == null) {//####[22]####
            __pt__paraTaskCreateArrays__ensureMethodVarSet();//####[22]####
        }//####[22]####
        taskinfo.setParameters();//####[22]####
        taskinfo.setMethod(__pt__paraTaskCreateArrays__method);//####[22]####
        taskinfo.setInstance(this);//####[22]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, NUM_OF_ARRAYS);//####[22]####
    }//####[22]####
    public void __pt__paraTaskCreateArrays() {//####[22]####
        double range = 4294967296.0;//####[24]####
        int min = -2147483648;//####[25]####
        int[] array = new int[LENGTH_OF_ARRAYS];//####[29]####
        for (int j = 0; j < array.length; j++) //####[30]####
        {//####[30]####
            array[j] = (int) (Math.random() * range) + min;//####[31]####
        }//####[32]####
        intArrays.add(array);//####[33]####
        System.out.println("Created array " + CurrentTask.relativeID());//####[34]####
    }//####[36]####
//####[36]####
//####[39]####
    private static volatile Method __pt__paraTaskSort_intAr_method = null;//####[39]####
    private synchronized static void __pt__paraTaskSort_intAr_ensureMethodVarSet() {//####[39]####
        if (__pt__paraTaskSort_intAr_method == null) {//####[39]####
            try {//####[39]####
                __pt__paraTaskSort_intAr_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskSort", new Class[] {//####[39]####
                    int[].class//####[39]####
                });//####[39]####
            } catch (Exception e) {//####[39]####
                e.printStackTrace();//####[39]####
            }//####[39]####
        }//####[39]####
    }//####[39]####
    public TaskID<Void> paraTaskSort(int[] array) {//####[39]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[39]####
        return paraTaskSort(array, new TaskInfo());//####[39]####
    }//####[39]####
    public TaskID<Void> paraTaskSort(int[] array, TaskInfo taskinfo) {//####[39]####
        // ensure Method variable is set//####[39]####
        if (__pt__paraTaskSort_intAr_method == null) {//####[39]####
            __pt__paraTaskSort_intAr_ensureMethodVarSet();//####[39]####
        }//####[39]####
        taskinfo.setParameters(array);//####[39]####
        taskinfo.setMethod(__pt__paraTaskSort_intAr_method);//####[39]####
        taskinfo.setInstance(this);//####[39]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[39]####
    }//####[39]####
    public TaskID<Void> paraTaskSort(TaskID<int[]> array) {//####[39]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[39]####
        return paraTaskSort(array, new TaskInfo());//####[39]####
    }//####[39]####
    public TaskID<Void> paraTaskSort(TaskID<int[]> array, TaskInfo taskinfo) {//####[39]####
        // ensure Method variable is set//####[39]####
        if (__pt__paraTaskSort_intAr_method == null) {//####[39]####
            __pt__paraTaskSort_intAr_ensureMethodVarSet();//####[39]####
        }//####[39]####
        taskinfo.setTaskIdArgIndexes(0);//####[39]####
        taskinfo.addDependsOn(array);//####[39]####
        taskinfo.setParameters(array);//####[39]####
        taskinfo.setMethod(__pt__paraTaskSort_intAr_method);//####[39]####
        taskinfo.setInstance(this);//####[39]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[39]####
    }//####[39]####
    public TaskID<Void> paraTaskSort(BlockingQueue<int[]> array) {//####[39]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[39]####
        return paraTaskSort(array, new TaskInfo());//####[39]####
    }//####[39]####
    public TaskID<Void> paraTaskSort(BlockingQueue<int[]> array, TaskInfo taskinfo) {//####[39]####
        // ensure Method variable is set//####[39]####
        if (__pt__paraTaskSort_intAr_method == null) {//####[39]####
            __pt__paraTaskSort_intAr_ensureMethodVarSet();//####[39]####
        }//####[39]####
        taskinfo.setQueueArgIndexes(0);//####[39]####
        taskinfo.setIsPipeline(true);//####[39]####
        taskinfo.setParameters(array);//####[39]####
        taskinfo.setMethod(__pt__paraTaskSort_intAr_method);//####[39]####
        taskinfo.setInstance(this);//####[39]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[39]####
    }//####[39]####
    public void __pt__paraTaskSort(int[] array) {//####[39]####
        System.out.println("Now sorting Array with Task ID: " + CurrentTask.globalID());//####[40]####
        ms.sort(array);//####[41]####
        System.out.println("Array with Task ID: " + CurrentTask.globalID() + " has been sorted by thread number: " + CurrentTask.currentThreadID());//####[42]####
    }//####[44]####
//####[44]####
//####[46]####
    private static volatile Method __pt__paraTaskProcess_intAr_intAr_String_method = null;//####[46]####
    private synchronized static void __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet() {//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            try {//####[46]####
                __pt__paraTaskProcess_intAr_intAr_String_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskProcess", new Class[] {//####[46]####
                    int[].class, int[].class, String.class//####[46]####
                });//####[46]####
            } catch (Exception e) {//####[46]####
                e.printStackTrace();//####[46]####
            }//####[46]####
        }//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, int[] array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, int[] array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, int[] array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, int[] array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(0);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, int[] array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, int[] array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, TaskID<int[]> array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, TaskID<int[]> array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, TaskID<int[]> array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, TaskID<int[]> array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(0, 1);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, TaskID<int[]> array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, TaskID<int[]> array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, BlockingQueue<int[]> array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, BlockingQueue<int[]> array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(1);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, BlockingQueue<int[]> array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, BlockingQueue<int[]> array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(1);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(0);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, BlockingQueue<int[]> array2, String dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, BlockingQueue<int[]> array2, String dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0, 1);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, int[] array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, int[] array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, int[] array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, int[] array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(0, 2);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, int[] array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, int[] array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, TaskID<int[]> array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, TaskID<int[]> array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(1, 2);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, TaskID<int[]> array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, TaskID<int[]> array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setTaskIdArgIndexes(0, 1, 2);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, TaskID<int[]> array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, TaskID<int[]> array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(1, 2);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, BlockingQueue<int[]> array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, BlockingQueue<int[]> array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(1);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, BlockingQueue<int[]> array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, BlockingQueue<int[]> array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(1);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(0, 2);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, BlockingQueue<int[]> array2, TaskID<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, BlockingQueue<int[]> array2, TaskID<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0, 1);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(2);//####[46]####
        taskinfo.addDependsOn(dependenceMessage);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, int[] array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, int[] array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, int[] array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, int[] array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(0);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, int[] array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, int[] array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0, 2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, TaskID<int[]> array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, TaskID<int[]> array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, TaskID<int[]> array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, TaskID<int[]> array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(0, 1);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, TaskID<int[]> array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, TaskID<int[]> array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0, 2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(1);//####[46]####
        taskinfo.addDependsOn(array2);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, BlockingQueue<int[]> array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(int[] array1, BlockingQueue<int[]> array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(1, 2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, BlockingQueue<int[]> array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(TaskID<int[]> array1, BlockingQueue<int[]> array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(1, 2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setTaskIdArgIndexes(0);//####[46]####
        taskinfo.addDependsOn(array1);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, BlockingQueue<int[]> array2, BlockingQueue<String> dependenceMessage) {//####[46]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[46]####
        return paraTaskProcess(array1, array2, dependenceMessage, new TaskInfo());//####[46]####
    }//####[46]####
    public TaskID<Void> paraTaskProcess(BlockingQueue<int[]> array1, BlockingQueue<int[]> array2, BlockingQueue<String> dependenceMessage, TaskInfo taskinfo) {//####[46]####
        // ensure Method variable is set//####[46]####
        if (__pt__paraTaskProcess_intAr_intAr_String_method == null) {//####[46]####
            __pt__paraTaskProcess_intAr_intAr_String_ensureMethodVarSet();//####[46]####
        }//####[46]####
        taskinfo.setQueueArgIndexes(0, 1, 2);//####[46]####
        taskinfo.setIsPipeline(true);//####[46]####
        taskinfo.setParameters(array1, array2, dependenceMessage);//####[46]####
        taskinfo.setMethod(__pt__paraTaskProcess_intAr_intAr_String_method);//####[46]####
        taskinfo.setInstance(this);//####[46]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[46]####
    }//####[46]####
    public void __pt__paraTaskProcess(int[] array1, int[] array2, String dependenceMessage) {//####[46]####
        System.out.println("Now processing Array with Task ID: " + CurrentTask.globalID() + dependenceMessage);//####[47]####
        if (array2.length == 0) //####[48]####
        {//####[48]####
            for (int i = 0; i < array1.length - 1; i++) //####[49]####
            array1[i] = array1[i] / 2 + array1[i + 1] / 2;//####[50]####
        } else {//####[51]####
            for (int i = 0; i < array1.length - 1; i++) //####[52]####
            array1[i] = array1[i] / 2 + array1[i + 1] / 2 + array2[i];//####[53]####
        }//####[54]####
        System.out.println("Array with Task ID: " + CurrentTask.globalID() + " has been processed by thread number: " + CurrentTask.currentThreadID());//####[55]####
    }//####[57]####
//####[57]####
//####[59]####
    private static volatile Method __pt__paraTaskProcess2_intAr_intAr_intAr_String_method = null;//####[59]####
    private synchronized static void __pt__paraTaskProcess2_intAr_intAr_intAr_String_ensureMethodVarSet() {//####[59]####
        if (__pt__paraTaskProcess2_intAr_intAr_intAr_String_method == null) {//####[59]####
            try {//####[59]####
                __pt__paraTaskProcess2_intAr_intAr_intAr_String_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__paraTaskProcess2", new Class[] {//####[59]####
                    int[].class, int[].class, int[].class, String.class//####[59]####
                });//####[59]####
            } catch (Exception e) {//####[59]####
                e.printStackTrace();//####[59]####
            }//####[59]####
        }//####[59]####
    }//####[59]####
    public TaskID<Void> paraTaskProcess2(Object array1, Object array2, Object array3, Object dependenceMessage) {//####[59]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[59]####
        return paraTaskProcess2(array1, array2, array3, dependenceMessage, new TaskInfo());//####[59]####
    }//####[59]####
    public TaskID<Void> paraTaskProcess2(Object array1, Object array2, Object array3, Object dependenceMessage, TaskInfo taskinfo) {//####[59]####
        // ensure Method variable is set//####[59]####
        if (__pt__paraTaskProcess2_intAr_intAr_intAr_String_method == null) {//####[59]####
            __pt__paraTaskProcess2_intAr_intAr_intAr_String_ensureMethodVarSet();//####[59]####
        }//####[59]####
        List<Integer> __pt__taskIdIndexList = new ArrayList<Integer>();//####[59]####
        List<Integer> __pt__queueIndexList = new ArrayList<Integer>();//####[59]####
        if (array1 instanceof BlockingQueue) {//####[59]####
            __pt__queueIndexList.add(0);//####[59]####
        }//####[59]####
        if (array1 instanceof TaskID) {//####[59]####
            taskinfo.addDependsOn((TaskID)array1);//####[59]####
            __pt__taskIdIndexList.add(0);//####[59]####
        }//####[59]####
        if (array2 instanceof BlockingQueue) {//####[59]####
            __pt__queueIndexList.add(1);//####[59]####
        }//####[59]####
        if (array2 instanceof TaskID) {//####[59]####
            taskinfo.addDependsOn((TaskID)array2);//####[59]####
            __pt__taskIdIndexList.add(1);//####[59]####
        }//####[59]####
        if (array3 instanceof BlockingQueue) {//####[59]####
            __pt__queueIndexList.add(2);//####[59]####
        }//####[59]####
        if (array3 instanceof TaskID) {//####[59]####
            taskinfo.addDependsOn((TaskID)array3);//####[59]####
            __pt__taskIdIndexList.add(2);//####[59]####
        }//####[59]####
        if (dependenceMessage instanceof BlockingQueue) {//####[59]####
            __pt__queueIndexList.add(3);//####[59]####
        }//####[59]####
        if (dependenceMessage instanceof TaskID) {//####[59]####
            taskinfo.addDependsOn((TaskID)dependenceMessage);//####[59]####
            __pt__taskIdIndexList.add(3);//####[59]####
        }//####[59]####
        int[] __pt__queueIndexArray = new int[__pt__queueIndexList.size()];//####[59]####
        for (int __pt__i = 0; __pt__i < __pt__queueIndexArray.length; __pt__i++) {//####[59]####
            __pt__queueIndexArray[__pt__i] = __pt__queueIndexList.get(__pt__i);//####[59]####
        }//####[59]####
        taskinfo.setQueueArgIndexes(__pt__queueIndexArray);//####[59]####
        if (__pt__queueIndexArray.length > 0) {//####[59]####
            taskinfo.setIsPipeline(true);//####[59]####
        }//####[59]####
        int[] __pt__taskIdIndexArray = new int[__pt__taskIdIndexList.size()];//####[59]####
        for (int __pt__i = 0; __pt__i < __pt__taskIdIndexArray.length; __pt__i++) {//####[59]####
            __pt__taskIdIndexArray[__pt__i] = __pt__taskIdIndexList.get(__pt__i);//####[59]####
        }//####[59]####
        taskinfo.setTaskIdArgIndexes(__pt__taskIdIndexArray);//####[59]####
        taskinfo.setParameters(array1, array2, array3, dependenceMessage);//####[59]####
        taskinfo.setMethod(__pt__paraTaskProcess2_intAr_intAr_intAr_String_method);//####[59]####
        taskinfo.setInstance(this);//####[59]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[59]####
    }//####[59]####
    public void __pt__paraTaskProcess2(int[] array1, int[] array2, int[] array3, String dependenceMessage) {//####[59]####
        System.out.println("Now processing Array with Task ID: " + CurrentTask.globalID() + dependenceMessage);//####[60]####
        for (int i = 0; i < array1.length - 1; i++) //####[61]####
        array1[i] = array1[i] / 2 + array1[i + 1] / 2 + array2[i] + array3[i];//####[62]####
        System.out.println("Array with Task ID: " + CurrentTask.globalID() + " has been processed by thread number: " + CurrentTask.currentThreadID());//####[63]####
    }//####[65]####
//####[65]####
//####[67]####
    private void start() {//####[67]####
        System.out.println("Initializing arrays in parallel..");//####[68]####
        TaskID<Void> arrayTasks = paraTaskCreateArrays();//####[69]####
        try {//####[70]####
            arrayTasks.waitTillFinished();//####[71]####
        } catch (ExecutionException e) {//####[72]####
            e.printStackTrace();//####[73]####
        } catch (InterruptedException e) {//####[74]####
            e.printStackTrace();//####[75]####
        }//####[76]####
        System.out.println("There are " + intArrays.size() + " arrays to be sorted and processed. Timer started.");//####[78]####
        long startTime = System.currentTimeMillis();//####[80]####
        String message = "";//####[81]####
        TaskID<Void> sortId0 = paraTaskSort(intArrays.get(0));//####[82]####
        TaskID<Void> sortId1 = paraTaskSort(intArrays.get(1));//####[83]####
        TaskID<Void> sortId2 = paraTaskSort(intArrays.get(2));//####[84]####
        TaskID<Void> sortId3 = paraTaskSort(intArrays.get(3));//####[85]####
        TaskID<Void> sortId4 = paraTaskSort(intArrays.get(4));//####[86]####
        TaskID<Void> sortId5 = paraTaskSort(intArrays.get(5));//####[87]####
        try {//####[88]####
            sortId0.waitTillFinished();//####[89]####
            TaskID<Void> processId0 = paraTaskProcess(intArrays.get(0), new int[0], message);//####[91]####
            sortId1.waitTillFinished();//####[93]####
            message = ", this task depends on Task " + processId0.globalID() + " which has completed.";//####[94]####
            TaskInfo __pt__processId1 = new TaskInfo();//####[95]####
//####[95]####
            /*  -- ParaTask dependsOn clause for 'processId1' -- *///####[95]####
            __pt__processId1.addDependsOn(processId0);//####[95]####
//####[95]####
            TaskID<Void> processId1 = paraTaskProcess(intArrays.get(1), intArrays.get(0), message, __pt__processId1);//####[95]####
            sortId2.waitTillFinished();//####[97]####
            TaskInfo __pt__processId2 = new TaskInfo();//####[98]####
//####[98]####
            /*  -- ParaTask dependsOn clause for 'processId2' -- *///####[98]####
            __pt__processId2.addDependsOn(processId0);//####[98]####
//####[98]####
            TaskID<Void> processId2 = paraTaskProcess(intArrays.get(2), intArrays.get(0), message, __pt__processId2);//####[98]####
            sortId2.waitTillFinished();//####[100]####
            TaskInfo __pt__processId3 = new TaskInfo();//####[101]####
//####[101]####
            /*  -- ParaTask dependsOn clause for 'processId3' -- *///####[101]####
            __pt__processId3.addDependsOn(processId0);//####[101]####
//####[101]####
            TaskID<Void> processId3 = paraTaskProcess(intArrays.get(3), intArrays.get(0), message, __pt__processId3);//####[101]####
            sortId4.waitTillFinished();//####[103]####
            message = ", this task depends on Tasks " + processId1.globalID() + " & " + processId2.globalID() + " which have completed.";//####[104]####
            TaskInfo __pt__processId4 = new TaskInfo();//####[105]####
//####[105]####
            /*  -- ParaTask dependsOn clause for 'processId4' -- *///####[105]####
            __pt__processId4.addDependsOn(processId1);//####[105]####
            __pt__processId4.addDependsOn(processId2);//####[105]####
//####[105]####
            TaskID<Void> processId4 = paraTaskProcess2(intArrays.get(4), intArrays.get(1), intArrays.get(2), message, __pt__processId4);//####[105]####
            sortId5.waitTillFinished();//####[107]####
            message = ", this task depends on Tasks " + processId2.globalID() + " & " + processId3.globalID() + " which have completed.";//####[108]####
            TaskInfo __pt__processId5 = new TaskInfo();//####[109]####
//####[109]####
            /*  -- ParaTask dependsOn clause for 'processId5' -- *///####[109]####
            __pt__processId5.addDependsOn(processId2);//####[109]####
            __pt__processId5.addDependsOn(processId3);//####[109]####
//####[109]####
            TaskID<Void> processId5 = paraTaskProcess2(intArrays.get(5), intArrays.get(2), intArrays.get(3), message, __pt__processId5);//####[109]####
            processId0.waitTillFinished();//####[111]####
            processId1.waitTillFinished();//####[112]####
            processId2.waitTillFinished();//####[113]####
            processId3.waitTillFinished();//####[114]####
            processId4.waitTillFinished();//####[115]####
            processId5.waitTillFinished();//####[116]####
        } catch (ExecutionException e) {//####[117]####
            e.printStackTrace();//####[118]####
        } catch (InterruptedException e) {//####[119]####
            e.printStackTrace();//####[120]####
        }//####[121]####
        long endTime = System.currentTimeMillis();//####[122]####
        System.out.println("Operations complete. Time taken: " + (endTime - startTime) / 1000.0 + " seconds");//####[123]####
    }//####[124]####
//####[126]####
    public static void main(String[] args) {//####[126]####
        ParaTaskWithDependency program = new ParaTaskWithDependency();//####[127]####
        program.start();//####[128]####
    }//####[129]####
}//####[129]####
