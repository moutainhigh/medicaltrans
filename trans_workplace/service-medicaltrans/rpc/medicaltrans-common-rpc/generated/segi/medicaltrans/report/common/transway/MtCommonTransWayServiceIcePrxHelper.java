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
// Generated from file `mt_transway_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transway;

/**
 * Provides type-specific helper functions.
 **/
public final class MtCommonTransWayServiceIcePrxHelper extends Ice.ObjectPrxHelperBase implements MtCommonTransWayServiceIcePrx
{
    private static final String __deleteTransWayById_name = "deleteTransWayById";

    public resp.RpcRespIce deleteTransWayById(int id)
    {
        return deleteTransWayById(id, null, false);
    }

    public resp.RpcRespIce deleteTransWayById(int id, java.util.Map<String, String> __ctx)
    {
        return deleteTransWayById(id, __ctx, true);
    }

    private resp.RpcRespIce deleteTransWayById(int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__deleteTransWayById_name);
        return end_deleteTransWayById(begin_deleteTransWayById(id, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id)
    {
        return begin_deleteTransWayById(id, null, false, false, null);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, java.util.Map<String, String> __ctx)
    {
        return begin_deleteTransWayById(id, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, Ice.Callback __cb)
    {
        return begin_deleteTransWayById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_deleteTransWayById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, Callback_MtCommonTransWayServiceIce_deleteTransWayById __cb)
    {
        return begin_deleteTransWayById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_deleteTransWayById __cb)
    {
        return begin_deleteTransWayById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_deleteTransWayById(id, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_deleteTransWayById(id, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_deleteTransWayById(id, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_deleteTransWayById(id, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                     java.util.Map<String, String> __ctx, 
                                                     boolean __explicitCtx, 
                                                     boolean __synchronous, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_deleteTransWayById(id, __ctx, __explicitCtx, __synchronous, 
                                        new IceInternal.Functional_TwowayCallbackArg1<resp.RpcRespIce>(__responseCb, __exceptionCb, __sentCb)
                                            {
                                                public final void __completed(Ice.AsyncResult __result)
                                                {
                                                    MtCommonTransWayServiceIcePrxHelper.__deleteTransWayById_completed(this, __result);
                                                }
                                            });
    }

    private Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                     java.util.Map<String, String> __ctx, 
                                                     boolean __explicitCtx, 
                                                     boolean __synchronous, 
                                                     IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__deleteTransWayById_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__deleteTransWayById_name, __cb);
        try
        {
            __result.prepare(__deleteTransWayById_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeInt(id);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public resp.RpcRespIce end_deleteTransWayById(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __deleteTransWayById_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            resp.RpcRespIceHolder __ret = new resp.RpcRespIceHolder();
            __is.readObject(__ret);
            __is.readPendingObjects();
            __result.endReadParams();
            return __ret.value;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __deleteTransWayById_completed(Ice.TwowayCallbackArg1<resp.RpcRespIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx __proxy = (segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx)__result.getProxy();
        resp.RpcRespIce __ret = null;
        try
        {
            __ret = __proxy.end_deleteTransWayById(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __getTransWayById_name = "getTransWayById";

    public TransWayInfoReturnIce getTransWayById(int id)
    {
        return getTransWayById(id, null, false);
    }

    public TransWayInfoReturnIce getTransWayById(int id, java.util.Map<String, String> __ctx)
    {
        return getTransWayById(id, __ctx, true);
    }

    private TransWayInfoReturnIce getTransWayById(int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getTransWayById_name);
        return end_getTransWayById(begin_getTransWayById(id, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getTransWayById(int id)
    {
        return begin_getTransWayById(id, null, false, false, null);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, java.util.Map<String, String> __ctx)
    {
        return begin_getTransWayById(id, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, Ice.Callback __cb)
    {
        return begin_getTransWayById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getTransWayById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, Callback_MtCommonTransWayServiceIce_getTransWayById __cb)
    {
        return begin_getTransWayById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_getTransWayById __cb)
    {
        return begin_getTransWayById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getTransWayById(id, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTransWayById(id, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getTransWayById(id, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTransWayById(id, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getTransWayById(int id, 
                                                  java.util.Map<String, String> __ctx, 
                                                  boolean __explicitCtx, 
                                                  boolean __synchronous, 
                                                  IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTransWayById(id, __ctx, __explicitCtx, __synchronous, 
                                     new IceInternal.Functional_TwowayCallbackArg1<segi.medicaltrans.report.common.transway.TransWayInfoReturnIce>(__responseCb, __exceptionCb, __sentCb)
                                         {
                                             public final void __completed(Ice.AsyncResult __result)
                                             {
                                                 MtCommonTransWayServiceIcePrxHelper.__getTransWayById_completed(this, __result);
                                             }
                                         });
    }

    private Ice.AsyncResult begin_getTransWayById(int id, 
                                                  java.util.Map<String, String> __ctx, 
                                                  boolean __explicitCtx, 
                                                  boolean __synchronous, 
                                                  IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getTransWayById_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getTransWayById_name, __cb);
        try
        {
            __result.prepare(__getTransWayById_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeInt(id);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public TransWayInfoReturnIce end_getTransWayById(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getTransWayById_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            TransWayInfoReturnIce __ret = null;
            __ret = TransWayInfoReturnIce.__read(__is, __ret);
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __getTransWayById_completed(Ice.TwowayCallbackArg1<TransWayInfoReturnIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx __proxy = (segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx)__result.getProxy();
        TransWayInfoReturnIce __ret = null;
        try
        {
            __ret = __proxy.end_getTransWayById(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __saveTransWay_name = "saveTransWay";

    public resp.RpcRespIce saveTransWay(TransWayInfo transWayInfo)
    {
        return saveTransWay(transWayInfo, null, false);
    }

    public resp.RpcRespIce saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx)
    {
        return saveTransWay(transWayInfo, __ctx, true);
    }

    private resp.RpcRespIce saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__saveTransWay_name);
        return end_saveTransWay(begin_saveTransWay(transWayInfo, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo)
    {
        return begin_saveTransWay(transWayInfo, null, false, false, null);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx)
    {
        return begin_saveTransWay(transWayInfo, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, Ice.Callback __cb)
    {
        return begin_saveTransWay(transWayInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_saveTransWay(transWayInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, Callback_MtCommonTransWayServiceIce_saveTransWay __cb)
    {
        return begin_saveTransWay(transWayInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_saveTransWay __cb)
    {
        return begin_saveTransWay(transWayInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_saveTransWay(transWayInfo, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_saveTransWay(transWayInfo, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_saveTransWay(transWayInfo, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_saveTransWay(transWayInfo, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                               IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_saveTransWay(transWayInfo, __ctx, __explicitCtx, __synchronous, 
                                  new IceInternal.Functional_TwowayCallbackArg1<resp.RpcRespIce>(__responseCb, __exceptionCb, __sentCb)
                                      {
                                          public final void __completed(Ice.AsyncResult __result)
                                          {
                                              MtCommonTransWayServiceIcePrxHelper.__saveTransWay_completed(this, __result);
                                          }
                                      });
    }

    private Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__saveTransWay_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__saveTransWay_name, __cb);
        try
        {
            __result.prepare(__saveTransWay_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            TransWayInfo.__write(__os, transWayInfo);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public resp.RpcRespIce end_saveTransWay(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __saveTransWay_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            resp.RpcRespIceHolder __ret = new resp.RpcRespIceHolder();
            __is.readObject(__ret);
            __is.readPendingObjects();
            __result.endReadParams();
            return __ret.value;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __saveTransWay_completed(Ice.TwowayCallbackArg1<resp.RpcRespIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx __proxy = (segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx)__result.getProxy();
        resp.RpcRespIce __ret = null;
        try
        {
            __ret = __proxy.end_saveTransWay(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __updateTransWayById_name = "updateTransWayById";

    public resp.RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo)
    {
        return updateTransWayById(id, transWayInfo, null, false);
    }

    public resp.RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx)
    {
        return updateTransWayById(id, transWayInfo, __ctx, true);
    }

    private resp.RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__updateTransWayById_name);
        return end_updateTransWayById(begin_updateTransWayById(id, transWayInfo, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo)
    {
        return begin_updateTransWayById(id, transWayInfo, null, false, false, null);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx)
    {
        return begin_updateTransWayById(id, transWayInfo, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, Ice.Callback __cb)
    {
        return begin_updateTransWayById(id, transWayInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_updateTransWayById(id, transWayInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, Callback_MtCommonTransWayServiceIce_updateTransWayById __cb)
    {
        return begin_updateTransWayById(id, transWayInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_updateTransWayById __cb)
    {
        return begin_updateTransWayById(id, transWayInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_updateTransWayById(id, transWayInfo, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_updateTransWayById(id, transWayInfo, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_updateTransWayById(id, transWayInfo, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_updateTransWayById(id, transWayInfo, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_updateTransWayById(int id, 
                                                     TransWayInfo transWayInfo, 
                                                     java.util.Map<String, String> __ctx, 
                                                     boolean __explicitCtx, 
                                                     boolean __synchronous, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_updateTransWayById(id, transWayInfo, __ctx, __explicitCtx, __synchronous, 
                                        new IceInternal.Functional_TwowayCallbackArg1<resp.RpcRespIce>(__responseCb, __exceptionCb, __sentCb)
                                            {
                                                public final void __completed(Ice.AsyncResult __result)
                                                {
                                                    MtCommonTransWayServiceIcePrxHelper.__updateTransWayById_completed(this, __result);
                                                }
                                            });
    }

    private Ice.AsyncResult begin_updateTransWayById(int id, 
                                                     TransWayInfo transWayInfo, 
                                                     java.util.Map<String, String> __ctx, 
                                                     boolean __explicitCtx, 
                                                     boolean __synchronous, 
                                                     IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__updateTransWayById_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__updateTransWayById_name, __cb);
        try
        {
            __result.prepare(__updateTransWayById_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeInt(id);
            TransWayInfo.__write(__os, transWayInfo);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public resp.RpcRespIce end_updateTransWayById(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __updateTransWayById_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            resp.RpcRespIceHolder __ret = new resp.RpcRespIceHolder();
            __is.readObject(__ret);
            __is.readPendingObjects();
            __result.endReadParams();
            return __ret.value;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __updateTransWayById_completed(Ice.TwowayCallbackArg1<resp.RpcRespIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx __proxy = (segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIcePrx)__result.getProxy();
        resp.RpcRespIce __ret = null;
        try
        {
            __ret = __proxy.end_updateTransWayById(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtCommonTransWayServiceIcePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), MtCommonTransWayServiceIcePrx.class, MtCommonTransWayServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtCommonTransWayServiceIcePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), MtCommonTransWayServiceIcePrx.class, MtCommonTransWayServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtCommonTransWayServiceIcePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), MtCommonTransWayServiceIcePrx.class, MtCommonTransWayServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtCommonTransWayServiceIcePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), MtCommonTransWayServiceIcePrx.class, MtCommonTransWayServiceIcePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static MtCommonTransWayServiceIcePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, MtCommonTransWayServiceIcePrx.class, MtCommonTransWayServiceIcePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static MtCommonTransWayServiceIcePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, MtCommonTransWayServiceIcePrx.class, MtCommonTransWayServiceIcePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::segi::medicaltrans::report::common::transway::MtCommonTransWayServiceIce"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, MtCommonTransWayServiceIcePrx v)
    {
        __os.writeProxy(v);
    }

    public static MtCommonTransWayServiceIcePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            MtCommonTransWayServiceIcePrxHelper result = new MtCommonTransWayServiceIcePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
