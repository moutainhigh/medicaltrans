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

public interface RatioReportServiceIcePrx extends Ice.ObjectPrx
{
    public TaskTypeReturnIce queryTaskTypeRatio(ReportCommonParam reportCommonParam);

    public TaskTypeReturnIce queryTaskTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, Callback_RatioReportServiceIce_queryTaskTypeRatio __cb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx, Callback_RatioReportServiceIce_queryTaskTypeRatio __cb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, 
                                                    IceInternal.Functional_GenericCallback1<TaskTypeReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, 
                                                    IceInternal.Functional_GenericCallback1<TaskTypeReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<TaskTypeReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryTaskTypeRatio(ReportCommonParam reportCommonParam, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<TaskTypeReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public TaskTypeReturnIce end_queryTaskTypeRatio(Ice.AsyncResult __result);

    public TranstypeReturnIce queryTransTypeRatio(ReportCommonParam reportCommonParam);

    public TranstypeReturnIce queryTransTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, Callback_RatioReportServiceIce_queryTransTypeRatio __cb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx, Callback_RatioReportServiceIce_queryTransTypeRatio __cb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, 
                                                     IceInternal.Functional_GenericCallback1<TranstypeReturnIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, 
                                                     IceInternal.Functional_GenericCallback1<TranstypeReturnIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, 
                                                     java.util.Map<String, String> __ctx, 
                                                     IceInternal.Functional_GenericCallback1<TranstypeReturnIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryTransTypeRatio(ReportCommonParam reportCommonParam, 
                                                     java.util.Map<String, String> __ctx, 
                                                     IceInternal.Functional_GenericCallback1<TranstypeReturnIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb);

    public TranstypeReturnIce end_queryTransTypeRatio(Ice.AsyncResult __result);

    public TaskDateSourceReturnIce queryTaskDateSourceRatio(ReportCommonParam reportCommonParam);

    public TaskDateSourceReturnIce queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, Callback_RatioReportServiceIce_queryTaskDateSourceRatio __cb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, java.util.Map<String, String> __ctx, Callback_RatioReportServiceIce_queryTaskDateSourceRatio __cb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, 
                                                          IceInternal.Functional_GenericCallback1<TaskDateSourceReturnIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, 
                                                          IceInternal.Functional_GenericCallback1<TaskDateSourceReturnIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, 
                                                          java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<TaskDateSourceReturnIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, 
                                                          java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<TaskDateSourceReturnIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb);

    public TaskDateSourceReturnIce end_queryTaskDateSourceRatio(Ice.AsyncResult __result);
}
