package ru.ssau.tk.lab2.io;

import ru.ssau.tk.lab2.functions.*;

import ru.ssau.tk.lab2.functions.factory.*;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());

        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String line = reader.readLine();
            String[] splitLine = line.split(" ");
            try {
                xValues[i] = numberFormat.parse(splitLine[0]).doubleValue();
                yValues[i] = numberFormat.parse(splitLine[1]).doubleValue();
            } catch (ParseException eParse) {
                throw new IOException(eParse);
            }
        }

        return factory.create(xValues, yValues);
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

    static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(stream);
        Object deserializedFunction = in.readObject();
        return (TabulatedFunction) deserializedFunction;
    }
}
