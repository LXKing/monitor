package mmp.gps.protocol.kangkaisi;

import java.util.concurrent.atomic.AtomicInteger;

public class KangkaisiUtil {

    private static AtomicInteger serialNumber = new AtomicInteger(65536);
    private static int[] crctab16 = new int[]{0, 4489, 8978, 12955, 17956, 22445, 25910, 29887, '\u8c48', '\u9dc1', '\uaf5a', '\ubed3', '\uca6c', '\udbe5', '\ue97e', '\uf8f7', 4225, 264, 13203, 8730, 22181, 18220, 30135, 25662, '\u9cc9', '\u8d40', '\ubfdb', '\uae52', '\udaed', '\ucb64', '\uf9ff', '\ue876', 8450, 12427, 528, 5017, 26406, 30383, 17460, 21949, '\uad4a', '\ubcc3', '\u8e58', '\u9fd1', '\ueb6e', '\ufae7', '\uc87c', '\ud9f5', 12675, 8202, 4753, 792, 30631, 26158, 21685, 17724, '\ubdcb', '\uac42', '\u9ed9', '\u8f50', '\ufbef', '\uea66', '\ud8fd', '\uc974', 16900, 21389, 24854, 28831, 1056, 5545, 10034, 14011, '\uce4c', '\udfc5', '\ued5e', '\ufcd7', '\u8868', '\u99e1', '\uab7a', '\ubaf3', 21125, 17164, 29079, 24606, 5281, 1320, 14259, 9786, '\udecd', '\ucf44', '\ufddf', '\uec56', '\u98e9', '\u8960', '\ubbfb', '\uaa72', 25350, 29327, 16404, 20893, 9506, 13483, 1584, 6073, '\uef4e', '\ufec7', '\ucc5c', '\uddd5', '\ua96a', '\ub8e3', '\u8a78', '\u9bf1', 29575, 25102, 20629, 16668, 13731, 9258, 5809, 1848, '\uffcf', '\uee46', '\udcdd', '\ucd54', '\ub9eb', '\ua862', '\u9af9', '\u8b70', '\u8408', '\u9581', '\ua71a', '\ub693', '\uc22c', '\ud3a5', '\ue13e', '\uf0b7', 2112, 6601, 11090, 15067, 20068, 24557, 28022, 31999, '\u9489', '\u8500', '\ub79b', '\ua612', '\ud2ad', '\uc324', '\uf1bf', '\ue036', 6337, 2376, 15315, 10842, 24293, 20332, 32247, 27774, '\ua50a', '\ub483', '\u8618', '\u9791', '\ue32e', '\uf2a7', '\uc03c', '\ud1b5', 10562, 14539, 2640, 7129, 28518, 32495, 19572, 24061, '\ub58b', '\ua402', '\u9699', '\u8710', '\uf3af', '\ue226', '\ud0bd', '\uc134', 14787, 10314, 6865, 2904, 32743, 28270, 23797, 19836, '\uc60c', '\ud785', '\ue51e', '\uf497', '\u8028', '\u91a1', '\ua33a', '\ub2b3', 19012, 23501, 26966, 30943, 3168, 7657, 12146, 16123, '\ud68d', '\uc704', '\uf59f', '\ue416', '\u90a9', '\u8120', '\ub3bb', '\ua232', 23237, 19276, 31191, 26718, 7393, 3432, 16371, 11898, '\ue70e', '\uf687', '\uc41c', '\ud595', '\ua12a', '\ub0a3', '\u8238', '\u93b1', 27462, 31439, 18516, 23005, 11618, 15595, 3696, 8185, '\uf78f', '\ue606', '\ud49d', '\uc514', '\ub1ab', '\ua022', '\u92b9', '\u8330', 31687, 27214, 22741, 18780, 15843, 11370, 7921, 3960};


    public static int getSerialNumber() {
        int index = serialNumber.incrementAndGet();
        if (index > '\uffff') {
            index = serialNumber.getAndSet(0);
        }

        return index;
    }

    public static int GetCrc16(byte[] raw, int nLength) {
        int fcs = '\uffff';

        for (int i = 2; nLength > 0; ++i) {
            fcs = fcs >> 8 ^ crctab16[(fcs ^ raw[i]) & 255];
            --nLength;
        }

        return ~fcs;
    }
}
