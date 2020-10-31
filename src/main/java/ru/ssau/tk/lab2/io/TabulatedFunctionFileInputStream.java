package ru.ssau.tk.lab2.io;

import ru.ssau.tk.lab2.functions.TabulatedFunction;
import ru.ssau.tk.lab2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lab2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.lab2.operations.TabulatedDifferetialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunction newFunction = FunctionsIO.readTabulatedFunction(in, new ArrayTabulatedFunctionFactory());
            System.out.println(newFunction.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции:");
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(in, new LinkedListTabulatedFunctionFactory());
            TabulatedDifferetialOperator differetialOperator = new TabulatedDifferetialOperator();
            TabulatedFunction derivedFunction = differetialOperator.derive(function);
            System.out.println(derivedFunction.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
