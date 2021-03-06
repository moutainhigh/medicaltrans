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

public abstract class _RatioReportServiceIceDisp extends Ice.ObjectImpl implements RatioReportServiceIce
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
        "::segi::medicaltrans::report::ratio::RatioReportServiceIce"
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

    public final TaskDateSourceReturnIce queryTaskDateSourceRatio(ReportCommonParam reportCommonParam)
    {
        return queryTaskDateSourceRatio(reportCommonParam, null);
    }

    public final TaskTypeReturnIce queryTaskTypeRatio(ReportCommonParam reportCommonParam)
    {
        return queryTaskTypeRatio(reportCommonParam, null);
    }

    public final TranstypeReturnIce queryTransTypeRatio(ReportCommonParam reportCommonParam)
    {
        return queryTransTypeRatio(reportCommonParam, null);
    }

    public static Ice.DispatchStatus ___queryTaskTypeRatio(RatioReportServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        ReportCommonParam reportCommonParam = null;
        reportCommonParam = ReportCommonParam.__read(__is, reportCommonParam);
        __inS.endReadParams();
        TaskTypeReturnIce __ret = __obj.queryTaskTypeRatio(reportCommonParam, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TaskTypeReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryTransTypeRatio(RatioReportServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        ReportCommonParam reportCommonParam = null;
        reportCommonParam = ReportCommonParam.__read(__is, reportCommonParam);
        __inS.endReadParams();
        TranstypeReturnIce __ret = __obj.queryTransTypeRatio(reportCommonParam, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TranstypeReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus ___queryTaskDateSourceRatio(RatioReportServiceIce __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.startReadParams();
        ReportCommonParam reportCommonParam = null;
        reportCommonParam = ReportCommonParam.__read(__is, reportCommonParam);
        __inS.endReadParams();
        TaskDateSourceReturnIce __ret = __obj.queryTaskDateSourceRatio(reportCommonParam, __current);
        IceInternal.BasicStream __os = __inS.__startWriteParams(Ice.FormatType.DefaultFormat);
        TaskDateSourceReturnIce.__write(__os, __ret);
        __inS.__endWriteParams(true);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "queryTaskDateSourceRatio",
        "queryTaskTypeRatio",
        "queryTransTypeRatio"
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
                return ___queryTaskDateSourceRatio(this, in, __current);
            }
            case 5:
            {
                return ___queryTaskTypeRatio(this, in, __current);
            }
            case 6:
            {
                return ___queryTransTypeRatio(this, in, __current);
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
