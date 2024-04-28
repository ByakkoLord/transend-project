package com.example.demo;

import java.util.List;

@SuppressWarnings("ALL")
public class BusPosicaoResult {
    public List<Linha> l;

    public static class Linha {
        public String c;
        public int cl;
        public int sl;
        public String lt0;
        public String lt1;
        public int qv;
        public List<Veiculo> vs;
    }

    public static class Veiculo {
        public int p;
        public boolean a;
        public String ta;
        public double py;
        public double px;
    }
}
