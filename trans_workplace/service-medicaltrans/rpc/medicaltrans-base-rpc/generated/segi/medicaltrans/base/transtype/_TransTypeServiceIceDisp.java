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
// Generated from file `transtype.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.transtype;

public abstract class _TransTypeServiceIceDisp extends Ice.ObjectImpl implements TransTypeServiceIce
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
        "::segi::medicaltrans::base::transtype::TransTypeServiceIce"
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

    public final TransTypeOprGuideRetIce queryOprGuide(TransTypeIce transTypeIce)
    {
        return queryOprGuide(transTypeIce, null);
    }

    public final TransTypeDetailReturnIce queryTransType(TransTypeIce transTypeIce)
    {
        return queryTransType(transTypeIce, null);
    }

    public final resp.RpcRespIce saveTransType(TransTypeDetailIce transTypeDetailIce)
    {
        return saveTransType(transTypeDetailIce, null);
    }

    public final TransTypeAllListIce transTypeAllList(TransTypeIce transTypeIce)
    {
        return transTypeAllList(transTypeIce, null);
    }

    public final TransTypeRetPageIce transTypeList(TransTypeIce transTypeIce)
    {
        return transTypeList(transTypeIce, null);
    }

    public final TransTypeRetPageIce transTypePage(TransTypeIce transTypeIce)
    {
        return transTypePage(transTypeIce, null);
    }

    public final resp.RpcRespIce updateStatusTransType(TransTypeIce transTypeIce)
    {
        return updateStatusTransType(transTypeIce, null);
    }

    public final resp.RpcRespIce updateTransType(TransTypeDetailIce transTypeDetailIce)
    {
        return updateTransType(transTypeDetailIce, null);
    }

    public static Ice.DispatchStatus ___saveTransType(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeDetailIceHolder transTypeDetailIce = new TransTypeDetailIceHolder();
        __is.readObject(transTypeDetailIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.saveTransType(transTypeDetailIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___updateTransType(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeDetailIceHolder transTypeDetailIce = new TransTypeDetailIceHolder();
        __is.readObject(transTypeDetailIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.updateTransType(transTypeDetailIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___updateStatusTransType(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeIceHolder transTypeIce = new TransTypeIceHolder();
        __is.readObject(transTypeIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        resp.RpcRespIce __ret = __obj.updateStatusTransType(transTypeIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        __os.writeObject(__ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryTransType(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeIceHolder transTypeIce = new TransTypeIceHolder();
        __is.readObject(transTypeIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        TransTypeDetailReturnIce __ret = __obj.queryTransType(transTypeIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TransTypeDetailReturnIce.__write(__os, __ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___transTypePage(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeIceHolder transTypeIce = new TransTypeIceHolder();
        __is.readObject(transTypeIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        TransTypeRetPageIce __ret = __obj.transTypePage(transTypeIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TransTypeRetPageIce.__write(__os, __ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___transTypeList(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeIceHolder transTypeIce = new TransTypeIceHolder();
        __is.readObject(transTypeIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        TransTypeRetPageIce __ret = __obj.transTypeList(transTypeIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TransTypeRetPageIce.__write(__os, __ret);
        __os.writePendingObjects();
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___transTypeAllList(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeIceHolder transTypeIce = new TransTypeIceHolder();
        __is.readObject(transTypeIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        TransTypeAllListIce __ret = __obj.transTypeAllList(transTypeIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TransTypeAllListIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryOprGuide(TransTypeServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        TransTypeIceHolder transTypeIce = new TransTypeIceHolder();
        __is.readObject(transTypeIce);
        __is.readPendingObjects();
        __inS.endReadParams();
        TransTypeOprGuideRetIce __ret = __obj.queryOprGuide(transTypeIce.value, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TransTypeOprGuideRetIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "queryOprGuide",
        "queryTransType",
        "saveTransType",
        "transTypeAllList",
        "transTypeList",
        "transTypePage",
        "updateStatusTransType",
        "updateTransType"
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
                return ___ice_id(this, in, __current);
            }
            case 1:
            {
                return ___ice_ids(this, in, __current);
            }
            case 2:
            {
                return ___ice_isA(this, in, __current);
            }
            case 3:
            {
                return ___ice_ping(this, in, __current);
            }
            case 4:
            {
                return ___queryOprGuide(this, in, __current);
            }
            case 5:
            {
                return ___queryTransType(this, in, __current);
            }
            case 6:
            {
                return ___saveTransType(this, in, __current);
            }
            case 7:
            {
                return ___transTypeAllList(this, in, __current);
            }
            case 8:
            {
                return ___transTypeList(this, in, __current);
            }
            case 9:
            {
                return ___transTypePage(this, in, __current);
            }
            case 10:
            {
                return ___updateStatusTransType(this, in, __current);
            }
            case 11:
            {
                return ___updateTransType(this, in, __current);
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
