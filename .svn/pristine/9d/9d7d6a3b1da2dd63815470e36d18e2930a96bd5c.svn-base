package com.edata.monitor.dao.locate;

import java.util.ArrayList;
import java.util.List;

/**
 * 实时数据块
 *
 * @author 生
 */
public class RealtimeDataBlock {

    private List<Latest> tracks;
    private List<Fault> faults;
    private List<Flow> flows;
    private List<InstructResult> replies;

    @Override
    public String toString() {
        return "RealtimeDataBlock{" + "tracks=" + tracks + ", faults=" + faults + ", flows=" + flows + ", replies=" +
                replies + '}';
    }

    /**
     * 获取位置数据列表
     */
    public List<Latest> getTracks() {
        return tracks;
    }

    /**
     * 设置位置数据列表
     */
    public void setTracks(List<Latest> tracks) {
        this.tracks = tracks;
    }

    /**
     * 添加位置数据
     */
    public void add(Latest track) {
        if (tracks == null)
            tracks = new ArrayList<Latest>();
        tracks.add(track);
    }

    /**
     * 获取故障数据列表
     */
    public List<Fault> getFaults() {
        return faults;
    }

    /**
     * 设置故障数据列表
     */
    public void setFaults(List<Fault> faults) {
        this.faults = faults;
    }

    /**
     * 添加故障数据
     */
    public void add(Fault fault) {
        if (faults == null)
            faults = new ArrayList<Fault>();
        faults.add(fault);
    }

    /**
     * 获取数据流列表
     */
    public List<Flow> getFlows() {
        return flows;
    }

    /**
     * 设置数据流列表
     */
    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    /**
     * 添加数据流
     */
    public void add(Flow flow) {
        if (flows == null)
            flows = new ArrayList<Flow>();
        flows.add(flow);
    }

    /**
     * 获取应答列表
     */
    public List<InstructResult> getReplies() {
        return replies;
    }

    /**
     * 设置应答列表
     */
    public void setReplies(List<InstructResult> replys) {
        this.replies = replys;
    }

    /**
     * 添加应答
     */
    public void add(InstructResult result) {
        if (replies == null)
            replies = new ArrayList<InstructResult>();
        replies.add(result);
    }
}
