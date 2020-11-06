package ru.ssau.tk.lab2.io;

import ru.ssau.tk.lab2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.lab2.operations.TabulatedDifferentialOperator;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))){
            final double[] xValues = {-3., -2., -1., 0., 1., 2., 3.};
            final double[] yValues = {9., 4., 1., 0., 1., 4., 9.};
            final TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction newFunction = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedFunction onceDerivedFunction = operator.derive(newFunction);
            TabulatedFunction twiceDerivedFunction = operator.derive(onceDerivedFunction);
            FunctionsIO.serialize(out, newFunction);
            FunctionsIO.serialize(out, onceDerivedFunction);
            FunctionsIO.serialize(out, twiceDerivedFunction);
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))){

            TabulatedFunction twiceDerivedFunction = FunctionsIO.deserialize(in);
            TabulatedFunction onceDerivedFunction = FunctionsIO.deserialize(in);
            TabulatedFunction function = FunctionsIO.deserialize(in);
            in.close();
            function.toString();
            onceDerivedFunction.toString();
            twiceDerivedFunction.toString();
        }
        catch (IOException | ClassNotFoundException e ){
            e.printStackTrace();
        }
    }
}
