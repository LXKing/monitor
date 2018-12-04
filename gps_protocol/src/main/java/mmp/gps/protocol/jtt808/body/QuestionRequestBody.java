package mmp.gps.protocol.jtt808.body;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionRequestBody implements IPacket {

    private byte flag;
    private short questionSize;
    private String question;
    private List answers;


    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public short getQuestionSize() {
        return this.questionSize;
    }

    public void setQuestionSize(short questionSize) {
        this.questionSize = questionSize;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
        this.questionSize = (short) (question == null ? 0 : this.question.getBytes(Charsets.GBK).length);
    }

    public List getAnswers() {
        return this.answers;
    }

    public void setAnswers(List answers) {
        this.answers = answers;
    }

    public int size() {
        int answersSize = 0;
        QuestionAnswerInfo info;
        if (this.answers != null) {
            for (Iterator var2 = this.answers.iterator(); var2.hasNext(); answersSize += info.size()) {
                info = (QuestionAnswerInfo) var2.next();
            }
        }

        return 2 + (this.question == null ? 0 : this.question.getBytes(Charsets.GBK).length) + answersSize;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.flag = io.get();
        this.questionSize = io.getUbyte();
        if (this.questionSize > 0) {
            this.question = new String(io.getBytes(this.questionSize), Charsets.GBK);
        }

        this.answers = new ArrayList();

        while (io.hasRemaining()) {
            QuestionAnswerInfo info = new QuestionAnswerInfo();
            info.from(io);
            this.answers.add(info);
        }

    }

    public byte[] array() {
        byte[] questionBytes = this.question.getBytes(Charsets.GBK);
        ByteIO io = new ByteIO(this.size());
        io.put(this.flag);
        io.putUbyte((short) questionBytes.length);
        io.put(questionBytes);
        Iterator var3 = this.answers.iterator();

        while (var3.hasNext()) {
            QuestionAnswerInfo info = (QuestionAnswerInfo) var3.next();
            io.put(info.array());
        }

        return io.array();
    }
}
