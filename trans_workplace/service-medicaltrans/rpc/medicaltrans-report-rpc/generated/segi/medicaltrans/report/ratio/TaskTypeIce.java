// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `ratio_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.ratio;

public class TaskTypeIce implements java.lang.Cloneable, java.io.Serializable
{
    public String dispatchTask;

    public String
    getDispatchTask()
    {
        return dispatchTask;
    }

    public void
    setDispatchTask(String _dispatchTask)
    {
        dispatchTask = _dispatchTask;
    }

    public String dispatchTaskAmount;

    public String
    getDispatchTaskAmount()
    {
        return dispatchTaskAmount;
    }

    public void
    setDispatchTaskAmount(String _dispatchTaskAmount)
    {
        dispatchTaskAmount = _dispatchTaskAmount;
    }

    public String autonomousTask;

    public String
    getAutonomousTask()
    {
        return autonomousTask;
    }

    public void
    setAutonomousTask(String _autonomousTask)
    {
        autonomousTask = _autonomousTask;
    }

    public String autonomousTaskAmount;

    public String
    getAutonomousTaskAmount()
    {
        return autonomousTaskAmount;
    }

    public void
    setAutonomousTaskAmount(String _autonomousTaskAmount)
    {
        autonomousTaskAmount = _autonomousTaskAmount;
    }

    public String fixedTask;

    public String
    getFixedTask()
    {
        return fixedTask;
    }

    public void
    setFixedTask(String _fixedTask)
    {
        fixedTask = _fixedTask;
    }

    public String fixedTaskAmount;

    public String
    getFixedTaskAmount()
    {
        return fixedTaskAmount;
    }

    public void
    setFixedTaskAmount(String _fixedTaskAmount)
    {
        fixedTaskAmount = _fixedTaskAmount;
    }

    public TaskTypeIce()
    {
        dispatchTask = "";
        dispatchTaskAmount = "";
        autonomousTask = "";
        autonomousTaskAmount = "";
        fixedTask = "";
        fixedTaskAmount = "";
    }

    public TaskTypeIce(String dispatchTask, String dispatchTaskAmount, String autonomousTask, String autonomousTaskAmount, String fixedTask, String fixedTaskAmount)
    {
        this.dispatchTask = dispatchTask;
        this.dispatchTaskAmount = dispatchTaskAmount;
        this.autonomousTask = autonomousTask;
        this.autonomousTaskAmount = autonomousTaskAmount;
        this.fixedTask = fixedTask;
        this.fixedTaskAmount = fixedTaskAmount;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TaskTypeIce _r = null;
        if(rhs instanceof TaskTypeIce)
        {
            _r = (TaskTypeIce)rhs;
        }

        if(_r != null)
        {
            if(dispatchTask != _r.dispatchTask)
            {
                if(dispatchTask == null || _r.dispatchTask == null || !dispatchTask.equals(_r.dispatchTask))
                {
                    return false;
                }
            }
            if(dispatchTaskAmount != _r.dispatchTaskAmount)
            {
                if(dispatchTaskAmount == null || _r.dispatchTaskAmount == null || !dispatchTaskAmount.equals(_r.dispatchTaskAmount))
                {
                    return false;
                }
            }
            if(autonomousTask != _r.autonomousTask)
            {
                if(autonomousTask == null || _r.autonomousTask == null || !autonomousTask.equals(_r.autonomousTask))
                {
                    return false;
                }
            }
            if(autonomousTaskAmount != _r.autonomousTaskAmount)
            {
                if(autonomousTaskAmount == null || _r.autonomousTaskAmount == null || !autonomousTaskAmount.equals(_r.autonomousTaskAmount))
                {
                    return false;
                }
            }
            if(fixedTask != _r.fixedTask)
            {
                if(fixedTask == null || _r.fixedTask == null || !fixedTask.equals(_r.fixedTask))
                {
                    return false;
                }
            }
            if(fixedTaskAmount != _r.fixedTaskAmount)
            {
                if(fixedTaskAmount == null || _r.fixedTaskAmount == null || !fixedTaskAmount.equals(_r.fixedTaskAmount))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::ratio::TaskTypeIce");
        __h = IceInternal.HashUtil.hashAdd(__h, dispatchTask);
        __h = IceInternal.HashUtil.hashAdd(__h, dispatchTaskAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, autonomousTask);
        __h = IceInternal.HashUtil.hashAdd(__h, autonomousTaskAmount);
        __h = IceInternal.HashUtil.hashAdd(__h, fixedTask);
        __h = IceInternal.HashUtil.hashAdd(__h, fixedTaskAmount);
        return __h;
    }

    public TaskTypeIce
    clone()
    {
        TaskTypeIce c = null;
        try
        {
            c = (TaskTypeIce)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(dispatchTask);
        __os.writeString(dispatchTaskAmount);
        __os.writeString(autonomousTask);
        __os.writeString(autonomousTaskAmount);
        __os.writeString(fixedTask);
        __os.writeString(fixedTaskAmount);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        dispatchTask = __is.readString();
        dispatchTaskAmount = __is.readString();
        autonomousTask = __is.readString();
        autonomousTaskAmount = __is.readString();
        fixedTask = __is.readString();
        fixedTaskAmount = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, TaskTypeIce __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public TaskTypeIce
    __read(IceInternal.BasicStream __is, TaskTypeIce __v)
    {
        if(__v == null)
        {
             __v = new TaskTypeIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TaskTypeIce __nullMarshalValue = new TaskTypeIce();

    public static final long serialVersionUID = -1204508421L;
}
