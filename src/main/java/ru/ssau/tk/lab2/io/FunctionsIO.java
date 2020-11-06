package ru.ssau.tk.lab2.io;

import ru.ssau.tk.lab2.functions.*;

import ru.ssau.tk.lab2.functions.factory.*;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import java.io.*;

final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException("Unavailable operation");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        int i = 0;
        for (Point a : function) {
            printWriter.printf("%f %f\n", a.x, a.y);
        }
        printWriter.flush();

    }

    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point currentPoint : TabulatedFunctionOperationService.asPoints(function)) {
            out.writeDouble(currentPoint.x);
            out.writeDouble(currentPoint.y);
        }
        out.flush();
    }

    static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream in = new DataInputStream(inputStream);
        int count = in.readInt();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = in.readDouble();
            yValues[i] = in.readDouble();
        }
        return factory.create(xValues, yValues);
    }

    static TabulatedFunction readTabulatedFunction(BufferedReader inputStream, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(inputStream.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = Double.parseDouble(inputStream.readLine());
            yValues[i] = Double.parseDouble(inputStream.readLine());
        }
        return factory.create(xValues, yValues);
    }

    static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(stream);
        Object deserializedFunction = in.readObject();
        return (TabulatedFunction) deserializedFunction;
    }
}
