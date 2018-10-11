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
// Generated from file `mt_transtype_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transtype;

/**
 * Provides type-specific helper functions.
 **/
public final class ReportCommonTransTypeServiceIcePrxHelper extends Ice.ObjectPrxHelperBase implements ReportCommonTransTypeServiceIcePrx
{
    private static final String __deleteTransTypeById_name = "deleteTransTypeById";

    public resp.RpcRespIce deleteTransTypeById(int id)
    {
        return deleteTransTypeById(id, null, false);
    }

    public resp.RpcRespIce deleteTransTypeById(int id, java.util.Map<String, String> __ctx)
    {
        return deleteTransTypeById(id, __ctx, true);
    }

    private resp.RpcRespIce deleteTransTypeById(int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__deleteTransTypeById_name);
        return end_deleteTransTypeById(begin_deleteTransTypeById(id, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id)
    {
        return begin_deleteTransTypeById(id, null, false, false, null);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, java.util.Map<String, String> __ctx)
    {
        return begin_deleteTransTypeById(id, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, Ice.Callback __cb)
    {
        return begin_deleteTransTypeById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_deleteTransTypeById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, Callback_ReportCommonTransTypeServiceIce_deleteTransTypeById __cb)
    {
        return begin_deleteTransTypeById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, java.util.Map<String, String> __ctx, Callback_ReportCommonTransTypeServiceIce_deleteTransTypeById __cb)
    {
        return begin_deleteTransTypeById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_deleteTransTypeById(id, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_deleteTransTypeById(id, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, 
                                                     java.util.Map<String, String> __ctx, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_deleteTransTypeById(id, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_deleteTransTypeById(int id, 
                                                     java.util.Map<String, String> __ctx, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_deleteTransTypeById(id, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_deleteTransTypeById(int id, 
                                                      java.util.Map<String, String> __ctx, 
                                                      boolean __explicitCtx, 
                                                      boolean __synchronous, 
                                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_deleteTransTypeById(id, __ctx, __explicitCtx, __synchronous, 
                                         new IceInternal.Functional_TwowayCallbackArg1<resp.RpcRespIce>(__responseCb, __exceptionCb, __sentCb)
                                             {
                                                 public final void __completed(Ice.AsyncResult __result)
                                                 {
                                                     ReportCommonTransTypeServiceIcePrxHelper.__deleteTransTypeById_completed(this, __result);
                                                 }
                                             });
    }

    private Ice.AsyncResult begin_deleteTransTypeById(int id, 
                                                      java.util.Map<String, String> __ctx, 
                                                      boolean __explicitCtx, 
                                                      boolean __synchronous, 
                                                      IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__deleteTransTypeById_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__deleteTransTypeById_name, __cb);
        try
        {
            __result.prepare(__deleteTransTypeById_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
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

    public resp.RpcRespIce end_deleteTransTypeById(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __deleteTransTypeById_name);
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

    static public void __deleteTransTypeById_completed(Ice.TwowayCallbackArg1<resp.RpcRespIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx __proxy = (segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx)__result.getProxy();
        resp.RpcRespIce __ret = null;
        try
        {
            __ret = __proxy.end_deleteTransTypeById(__result);
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

    private static final String __getTransTypeById_name = "getTransTypeById";

    public TransTypeInfoReturnIce getTransTypeById(int id)
    {
        return getTransTypeById(id, null, false);
    }

    public TransTypeInfoReturnIce getTransTypeById(int id, java.util.Map<String, String> __ctx)
    {
        return getTransTypeById(id, __ctx, true);
    }

    private TransTypeInfoReturnIce getTransTypeById(int id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getTransTypeById_name);
        return end_getTransTypeById(begin_getTransTypeById(id, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getTransTypeById(int id)
    {
        return begin_getTransTypeById(id, null, false, false, null);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, java.util.Map<String, String> __ctx)
    {
        return begin_getTransTypeById(id, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, Ice.Callback __cb)
    {
        return begin_getTransTypeById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getTransTypeById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, Callback_ReportCommonTransTypeServiceIce_getTransTypeById __cb)
    {
        return begin_getTransTypeById(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, java.util.Map<String, String> __ctx, Callback_ReportCommonTransTypeServiceIce_getTransTypeById __cb)
    {
        return begin_getTransTypeById(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, 
                                                  IceInternal.Functional_GenericCallback1<TransTypeInfoReturnIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getTransTypeById(id, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, 
                                                  IceInternal.Functional_GenericCallback1<TransTypeInfoReturnIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTransTypeById(id, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<TransTypeInfoReturnIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getTransTypeById(id, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getTransTypeById(int id, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<TransTypeInfoReturnIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTransTypeById(id, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getTransTypeById(int id, 
                                                   java.util.Map<String, String> __ctx, 
                                                   boolean __explicitCtx, 
                                                   boolean __synchronous, 
                                                   IceInternal.Functional_GenericCallback1<TransTypeInfoReturnIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getTransTypeById(id, __ctx, __explicitCtx, __synchronous, 
                                      new IceInternal.Functional_TwowayCallbackArg1<segi.medicaltrans.report.common.transtype.TransTypeInfoReturnIce>(__responseCb, __exceptionCb, __sentCb)
                                          {
                                              public final void __completed(Ice.AsyncResult __result)
                                              {
                                                  ReportCommonTransTypeServiceIcePrxHelper.__getTransTypeById_completed(this, __result);
                                              }
                                          });
    }

    private Ice.AsyncResult begin_getTransTypeById(int id, 
                                                   java.util.Map<String, String> __ctx, 
                                                   boolean __explicitCtx, 
                                                   boolean __synchronous, 
                                                   IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getTransTypeById_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getTransTypeById_name, __cb);
        try
        {
            __result.prepare(__getTransTypeById_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
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

    public TransTypeInfoReturnIce end_getTransTypeById(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getTransTypeById_name);
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
            TransTypeInfoReturnIce __ret = null;
            __ret = TransTypeInfoReturnIce.__read(__is, __ret);
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

    static public void __getTransTypeById_completed(Ice.TwowayCallbackArg1<TransTypeInfoReturnIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx __proxy = (segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx)__result.getProxy();
        TransTypeInfoReturnIce __ret = null;
        try
        {
            __ret = __proxy.end_getTransTypeById(__result);
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

    private static final String __saveTransType_name = "saveTransType";

    public resp.RpcRespIce saveTransType(TransTypeInfo transTypeInfo)
    {
        return saveTransType(transTypeInfo, null, false);
    }

    public resp.RpcRespIce saveTransType(TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx)
    {
        return saveTransType(transTypeInfo, __ctx, true);
    }

    private resp.RpcRespIce saveTransType(TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__saveTransType_name);
        return end_saveTransType(begin_saveTransType(transTypeInfo, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo)
    {
        return begin_saveTransType(transTypeInfo, null, false, false, null);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx)
    {
        return begin_saveTransType(transTypeInfo, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, Ice.Callback __cb)
    {
        return begin_saveTransType(transTypeInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_saveTransType(transTypeInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, Callback_ReportCommonTransTypeServiceIce_saveTransType __cb)
    {
        return begin_saveTransType(transTypeInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx, Callback_ReportCommonTransTypeServiceIce_saveTransType __cb)
    {
        return begin_saveTransType(transTypeInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, 
                                               IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_saveTransType(transTypeInfo, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, 
                                               IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                               IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_saveTransType(transTypeInfo, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, 
                                               java.util.Map<String, String> __ctx, 
                                               IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_saveTransType(transTypeInfo, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, 
                                               java.util.Map<String, String> __ctx, 
                                               IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                               IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_saveTransType(transTypeInfo, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, 
                                                java.util.Map<String, String> __ctx, 
                                                boolean __explicitCtx, 
                                                boolean __synchronous, 
                                                IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_saveTransType(transTypeInfo, __ctx, __explicitCtx, __synchronous, 
                                   new IceInternal.Functional_TwowayCallbackArg1<resp.RpcRespIce>(__responseCb, __exceptionCb, __sentCb)
                                       {
                                           public final void __completed(Ice.AsyncResult __result)
                                           {
                                               ReportCommonTransTypeServiceIcePrxHelper.__saveTransType_completed(this, __result);
                                           }
                                       });
    }

    private Ice.AsyncResult begin_saveTransType(TransTypeInfo transTypeInfo, 
                                                java.util.Map<String, String> __ctx, 
                                                boolean __explicitCtx, 
                                                boolean __synchronous, 
                                                IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__saveTransType_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__saveTransType_name, __cb);
        try
        {
            __result.prepare(__saveTransType_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            TransTypeInfo.__write(__os, transTypeInfo);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public resp.RpcRespIce end_saveTransType(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __saveTransType_name);
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

    static public void __saveTransType_completed(Ice.TwowayCallbackArg1<resp.RpcRespIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx __proxy = (segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx)__result.getProxy();
        resp.RpcRespIce __ret = null;
        try
        {
            __ret = __proxy.end_saveTransType(__result);
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

    private static final String __updateTransTypeById_name = "updateTransTypeById";

    public resp.RpcRespIce updateTransTypeById(int id, TransTypeInfo transTypeInfo)
    {
        return updateTransTypeById(id, transTypeInfo, null, false);
    }

    public resp.RpcRespIce updateTransTypeById(int id, TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx)
    {
        return updateTransTypeById(id, transTypeInfo, __ctx, true);
    }

    private resp.RpcRespIce updateTransTypeById(int id, TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__updateTransTypeById_name);
        return end_updateTransTypeById(begin_updateTransTypeById(id, transTypeInfo, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, TransTypeInfo transTypeInfo)
    {
        return begin_updateTransTypeById(id, transTypeInfo, null, false, false, null);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx)
    {
        return begin_updateTransTypeById(id, transTypeInfo, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, TransTypeInfo transTypeInfo, Ice.Callback __cb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, TransTypeInfo transTypeInfo, Callback_ReportCommonTransTypeServiceIce_updateTransTypeById __cb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, TransTypeInfo transTypeInfo, java.util.Map<String, String> __ctx, Callback_ReportCommonTransTypeServiceIce_updateTransTypeById __cb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, 
                                                     TransTypeInfo transTypeInfo, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, 
                                                     TransTypeInfo transTypeInfo, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, 
                                                     TransTypeInfo transTypeInfo, 
                                                     java.util.Map<String, String> __ctx, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_updateTransTypeById(int id, 
                                                     TransTypeInfo transTypeInfo, 
                                                     java.util.Map<String, String> __ctx, 
                                                     IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_updateTransTypeById(int id, 
                                                      TransTypeInfo transTypeInfo, 
                                                      java.util.Map<String, String> __ctx, 
                                                      boolean __explicitCtx, 
                                                      boolean __synchronous, 
                                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_updateTransTypeById(id, transTypeInfo, __ctx, __explicitCtx, __synchronous, 
                                         new IceInternal.Functional_TwowayCallbackArg1<resp.RpcRespIce>(__responseCb, __exceptionCb, __sentCb)
                                             {
                                                 public final void __completed(Ice.AsyncResult __result)
                                                 {
                                                     ReportCommonTransTypeServiceIcePrxHelper.__updateTransTypeById_completed(this, __result);
                                                 }
                                             });
    }

    private Ice.AsyncResult begin_updateTransTypeById(int id, 
                                                      TransTypeInfo transTypeInfo, 
                                                      java.util.Map<String, String> __ctx, 
                                                      boolean __explicitCtx, 
                                                      boolean __synchronous, 
                                                      IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__updateTransTypeById_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__updateTransTypeById_name, __cb);
        try
        {
            __result.prepare(__updateTransTypeById_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeInt(id);
            TransTypeInfo.__write(__os, transTypeInfo);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public resp.RpcRespIce end_updateTransTypeById(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __updateTransTypeById_name);
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

    static public void __updateTransTypeById_completed(Ice.TwowayCallbackArg1<resp.RpcRespIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx __proxy = (segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIcePrx)__result.getProxy();
        resp.RpcRespIce __ret = null;
        try
        {
            __ret = __proxy.end_updateTransTypeById(__result);
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
    public static ReportCommonTransTypeServiceIcePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), ReportCommonTransTypeServiceIcePrx.class, ReportCommonTransTypeServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static ReportCommonTransTypeServiceIcePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), ReportCommonTransTypeServiceIcePrx.class, ReportCommonTransTypeServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static ReportCommonTransTypeServiceIcePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), ReportCommonTransTypeServiceIcePrx.class, ReportCommonTransTypeServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static ReportCommonTransTypeServiceIcePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), ReportCommonTransTypeServiceIcePrx.class, ReportCommonTransTypeServiceIcePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static ReportCommonTransTypeServiceIcePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, ReportCommonTransTypeServiceIcePrx.class, ReportCommonTransTypeServiceIcePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static ReportCommonTransTypeServiceIcePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, ReportCommonTransTypeServiceIcePrx.class, ReportCommonTransTypeServiceIcePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::segi::medicaltrans::report::common::transtype::ReportCommonTransTypeServiceIce"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, ReportCommonTransTypeServiceIcePrx v)
    {
        __os.writeProxy(v);
    }

    public static ReportCommonTransTypeServiceIcePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ReportCommonTransTypeServiceIcePrxHelper result = new ReportCommonTransTypeServiceIcePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
