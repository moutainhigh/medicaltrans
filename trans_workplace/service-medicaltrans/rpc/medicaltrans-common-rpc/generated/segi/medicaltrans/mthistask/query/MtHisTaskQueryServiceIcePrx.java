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
// Generated from file `mt_his_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mthistask.query;

public interface MtHisTaskQueryServiceIcePrx extends Ice.ObjectPrx
{
    public MtTaskPaginatorIceRsp queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam);

    public MtTaskPaginatorIceRsp queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskPage __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskPage __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskPaginatorIceRsp end_queryMtHisTaskPage(Ice.AsyncResult __result);

    public FixedTaskHisExePageRsp queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam);

    public FixedTaskHisExePageRsp queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, Callback_MtHisTaskQueryServiceIce_queryMtHisFixedTaskExeInfoPage __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryMtHisFixedTaskExeInfoPage __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                                IceInternal.Functional_GenericCallback1<FixedTaskHisExePageRsp> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                                IceInternal.Functional_GenericCallback1<FixedTaskHisExePageRsp> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                                java.util.Map<String, String> __ctx, 
                                                                IceInternal.Functional_GenericCallback1<FixedTaskHisExePageRsp> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                                java.util.Map<String, String> __ctx, 
                                                                IceInternal.Functional_GenericCallback1<FixedTaskHisExePageRsp> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                IceInternal.Functional_BoolCallback __sentCb);

    public FixedTaskHisExePageRsp end_queryMtHisFixedTaskExeInfoPage(Ice.AsyncResult __result);

    public MtTaskDetailRetIceRsp queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam);

    public MtTaskDetailRetIceRsp queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskDetail __cb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskDetail __cb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                      IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                      IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                      java.util.Map<String, String> __ctx, 
                                                      IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                      java.util.Map<String, String> __ctx, 
                                                      IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskDetailRetIceRsp end_queryMtHisTaskDetail(Ice.AsyncResult __result);

    public MtTaskDetailRetIceRsp queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam);

    public MtTaskDetailRetIceRsp queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskFixedDetail __cb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskFixedDetail __cb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                           IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                           java.util.Map<String, String> __ctx, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                           java.util.Map<String, String> __ctx, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskDetailRetIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                           IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskDetailRetIceRsp end_queryMtHisTaskFixedDetail(Ice.AsyncResult __result);

    public FixedTaskExeDetailReturnIce queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam);

    public FixedTaskExeDetailReturnIce queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, Callback_MtHisTaskQueryServiceIce_queryMtHisFixedTaskExeDetail __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryMtHisFixedTaskExeDetail __cb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                              IceInternal.Functional_GenericCallback1<FixedTaskExeDetailReturnIce> __responseCb, 
                                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                              IceInternal.Functional_GenericCallback1<FixedTaskExeDetailReturnIce> __responseCb, 
                                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                              IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                              java.util.Map<String, String> __ctx, 
                                                              IceInternal.Functional_GenericCallback1<FixedTaskExeDetailReturnIce> __responseCb, 
                                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam, 
                                                              java.util.Map<String, String> __ctx, 
                                                              IceInternal.Functional_GenericCallback1<FixedTaskExeDetailReturnIce> __responseCb, 
                                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                              IceInternal.Functional_BoolCallback __sentCb);

    public FixedTaskExeDetailReturnIce end_queryMtHisFixedTaskExeDetail(Ice.AsyncResult __result);

    public MtTaskPaginatorIceRsp queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam);

    public MtTaskPaginatorIceRsp queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskPageByUser __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryMtHisTaskPageByUser __cb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, 
                                                          IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, 
                                                          IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, 
                                                          java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam, 
                                                          java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskPaginatorIceRsp end_queryMtHisTaskPageByUser(Ice.AsyncResult __result);

    public MtTaskPaginatorIceRsp getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam);

    public MtTaskPaginatorIceRsp getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, Callback_MtHisTaskQueryServiceIce_getDeptTaskPage __cb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_getDeptTaskPage __cb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, 
                                                 IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, 
                                                 IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskPaginatorIceRsp end_getDeptTaskPage(Ice.AsyncResult __result);

    public MtTaskPaginatorIceRsp queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam);

    public MtTaskPaginatorIceRsp queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, Callback_MtHisTaskQueryServiceIce_queryUserHisTaskDaylyPage __cb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, java.util.Map<String, String> __ctx, Callback_MtHisTaskQueryServiceIce_queryUserHisTaskDaylyPage __cb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                           IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                           java.util.Map<String, String> __ctx, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam, 
                                                           java.util.Map<String, String> __ctx, 
                                                           IceInternal.Functional_GenericCallback1<MtTaskPaginatorIceRsp> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                           IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskPaginatorIceRsp end_queryUserHisTaskDaylyPage(Ice.AsyncResult __result);
}