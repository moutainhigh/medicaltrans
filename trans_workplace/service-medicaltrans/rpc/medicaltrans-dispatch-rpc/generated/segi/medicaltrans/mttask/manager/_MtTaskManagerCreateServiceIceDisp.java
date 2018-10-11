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
// Generated from file `mt_task_manager.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.manager;

public abstract class _MtTaskManagerCreateServiceIceDisp extends Ice.ObjectImpl implements MtTaskManagerCreateServiceIce
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::segi::medicaltrans::mttask::manager::MtTaskManagerCreateServiceIce"
    };

    public boolean ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[] ice_ids()
    {
        return __ids;
    }

    public String[] ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String ice_id()
    {
        return __ids[1];
    }

    public String ice_id(Ice.Current __current)
    {
        return __ids[1];
    }

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public final resp.RpcRespIce againDispatchTask(SendTaskIceParam sendTaskIceParam)
    {
        return againDispatchTask(sendTaskIceParam, null);
    }

    public final resp.RpcRespIce againFixedTask(SendTaskIceParam sendTaskIceParam)
    {
        return againFixedTask(sendTaskIceParam, null);
    }

    public final resp.RpcRespIce createFixedTask(MtFixedTaskParam param)
    {
        return createFixedTask(param, null);
    }

    public final resp.RpcRespIce createTask(MtTaskReq param)
    {
        return createTask(param, null);
    }

    public final resp.RpcRespIce dispatchTask(SendTaskIceParam sendTaskIceParam)
    {
        return dispatchTask(sendTaskIceParam, null);
    }

    public final resp.RpcRespIce editTask(MtTaskReq param)
    {
        return editTask(param, null);
    }

    public static Ice.DispatchStatus ___createTask(MtTaskManagerCreateServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        MtTaskReq param = null;
        param = MtTaskReq.__read(__is, param);
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.createTask(param, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___createFixedTask(MtTaskManagerCreateServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        MtFixedTaskParam param = null;
        param = MtFixedTaskParam.__read(__is, param);
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.createFixedTask(param, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___dispatchTask(MtTaskManagerCreateServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        SendTaskIceParam sendTaskIceParam = null;
        sendTaskIceParam = SendTaskIceParam.__read(__is, sendTaskIceParam);
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.dispatchTask(sendTaskIceParam, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___againDispatchTask(MtTaskManagerCreateServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        SendTaskIceParam sendTaskIceParam = null;
        sendTaskIceParam = SendTaskIceParam.__read(__is, sendTaskIceParam);
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.againDispatchTask(sendTaskIceParam, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___againFixedTask(MtTaskManagerCreateServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        SendTaskIceParam sendTaskIceParam = null;
        sendTaskIceParam = SendTaskIceParam.__read(__is, sendTaskIceParam);
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.againFixedTask(sendTaskIceParam, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___editTask(MtTaskManagerCreateServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        MtTaskReq param = null;
        param = MtTaskReq.__read(__is, param);
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.editTask(param, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "againDispatchTask",
        "againFixedTask",
        "createFixedTask",
        "createTask",
        "dispatchTask",
        "editTask",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping"
    };

    public Ice.DispatchStatus __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___againDispatchTask(this, in, __current);
            }
            case 1:
            {
                return ___againFixedTask(this, in, __current);
            }
            case 2:
            {
                return ___createFixedTask(this, in, __current);
            }
            case 3:
            {
                return ___createTask(this, in, __current);
            }
            case 4:
            {
                return ___dispatchTask(this, in, __current);
            }
            case 5:
            {
                return ___editTask(this, in, __current);
            }
            case 6:
            {
                return ___ice_id(this, in, __current);
            }
            case 7:
            {
                return ___ice_ids(this, in, __current);
            }
            case 8:
            {
                return ___ice_isA(this, in, __current);
            }
            case 9:
            {
                return ___ice_ping(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    protected void __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice(ice_staticId(), -1, true);
        __os.endWriteSlice();
    }

    protected void __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = 0L;
}
