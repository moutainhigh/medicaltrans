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
// Generated from file `mt_db_source.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.dbsource;

/**
 * Provides type-specific helper functions.
 **/
public final class MtDbSourceRuleServiceIcePrxHelper extends Ice.ObjectPrxHelperBase implements MtDbSourceRuleServiceIcePrx
{
    private static final String __getDbSourceRuleByGroupId_name = "getDbSourceRuleByGroupId";

    public DbSourceRuleRspIce getDbSourceRuleByGroupId(int groupOrganId)
    {
        return getDbSourceRuleByGroupId(groupOrganId, null, false);
    }

    public DbSourceRuleRspIce getDbSourceRuleByGroupId(int groupOrganId, java.util.Map<String, String> __ctx)
    {
        return getDbSourceRuleByGroupId(groupOrganId, __ctx, true);
    }

    private DbSourceRuleRspIce getDbSourceRuleByGroupId(int groupOrganId, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getDbSourceRuleByGroupId_name);
        return end_getDbSourceRuleByGroupId(begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, null, false, false, null);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, java.util.Map<String, String> __ctx)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, Ice.Callback __cb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, Callback_MtDbSourceRuleServiceIce_getDbSourceRuleByGroupId __cb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, java.util.Map<String, String> __ctx, Callback_MtDbSourceRuleServiceIce_getDbSourceRuleByGroupId __cb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, 
                                                          IceInternal.Functional_GenericCallback1<DbSourceRuleRspIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, 
                                                          IceInternal.Functional_GenericCallback1<DbSourceRuleRspIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, 
                                                          java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<DbSourceRuleRspIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, 
                                                          java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<DbSourceRuleRspIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, 
                                                           java.util.Map<String, String> __ctx, 
                                                           boolean __explicitCtx, 
                                                           boolean __synchronous, 
                                                           IceInternal.Functional_GenericCallback1<DbSourceRuleRspIce> __responseCb, 
                                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getDbSourceRuleByGroupId(groupOrganId, __ctx, __explicitCtx, __synchronous, 
                                              new IceInternal.Functional_TwowayCallbackArg1<segi.medicaltrans.base.dbsource.DbSourceRuleRspIce>(__responseCb, __exceptionCb, __sentCb)
                                                  {
                                                      public final void __completed(Ice.AsyncResult __result)
                                                      {
                                                          MtDbSourceRuleServiceIcePrxHelper.__getDbSourceRuleByGroupId_completed(this, __result);
                                                      }
                                                  });
    }

    private Ice.AsyncResult begin_getDbSourceRuleByGroupId(int groupOrganId, 
                                                           java.util.Map<String, String> __ctx, 
                                                           boolean __explicitCtx, 
                                                           boolean __synchronous, 
                                                           IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getDbSourceRuleByGroupId_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getDbSourceRuleByGroupId_name, __cb);
        try
        {
            __result.prepare(__getDbSourceRuleByGroupId_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeInt(groupOrganId);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public DbSourceRuleRspIce end_getDbSourceRuleByGroupId(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getDbSourceRuleByGroupId_name);
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
            DbSourceRuleRspIce __ret = null;
            __ret = DbSourceRuleRspIce.__read(__is, __ret);
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

    static public void __getDbSourceRuleByGroupId_completed(Ice.TwowayCallbackArg1<DbSourceRuleRspIce> __cb, Ice.AsyncResult __result)
    {
        segi.medicaltrans.base.dbsource.MtDbSourceRuleServiceIcePrx __proxy = (segi.medicaltrans.base.dbsource.MtDbSourceRuleServiceIcePrx)__result.getProxy();
        DbSourceRuleRspIce __ret = null;
        try
        {
            __ret = __proxy.end_getDbSourceRuleByGroupId(__result);
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
    public static MtDbSourceRuleServiceIcePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), MtDbSourceRuleServiceIcePrx.class, MtDbSourceRuleServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtDbSourceRuleServiceIcePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), MtDbSourceRuleServiceIcePrx.class, MtDbSourceRuleServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtDbSourceRuleServiceIcePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), MtDbSourceRuleServiceIcePrx.class, MtDbSourceRuleServiceIcePrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static MtDbSourceRuleServiceIcePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), MtDbSourceRuleServiceIcePrx.class, MtDbSourceRuleServiceIcePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static MtDbSourceRuleServiceIcePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, MtDbSourceRuleServiceIcePrx.class, MtDbSourceRuleServiceIcePrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static MtDbSourceRuleServiceIcePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, MtDbSourceRuleServiceIcePrx.class, MtDbSourceRuleServiceIcePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::segi::medicaltrans::base::dbsource::MtDbSourceRuleServiceIce"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, MtDbSourceRuleServiceIcePrx v)
    {
        __os.writeProxy(v);
    }

    public static MtDbSourceRuleServiceIcePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            MtDbSourceRuleServiceIcePrxHelper result = new MtDbSourceRuleServiceIcePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
