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
// Generated from file `mt_task_track.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.track;

public interface _MtTaskTrackServiceIceOperations
{
    resp.RpcRespIce saveTrackForCreateTask(String taskId, int organId, String beginTime, java.util.List<ItemIce> itemList, Ice.Current __current);

    resp.RpcRespIce saveTaskTrackMessage(int taskId, ItemIce itemIce, Ice.Current __current);

    TrackDetailRspIce queryTaskTrackById(int id, Ice.Current __current);

    resp.RpcRespIce updateTrackForEditTask(String taskId, int organId, String beginTime, Ice.Current __current);
}