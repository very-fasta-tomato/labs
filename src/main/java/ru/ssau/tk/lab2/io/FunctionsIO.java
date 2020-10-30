package ru.ssau.tk.lab2.io;

import ru.ssau.tk.lab2.functions.*;

import ru.ssau.tk.lab2.functions.factory.*;
import ru.ssau.tk.lab2.operations.TabulatedFunctionOperationService;

import java.io.*;

final class FunctionsIO {
    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point currentPoint : TabulatedFunctionOperationService.asPoints(function)){
            out.writeDouble(currentPoint.x);
            out.writeDouble(currentPoint.y);
        }
        out.flush();
    }
    /*
    static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory){
        DataInputStream in = new DataInputStream(inputStream);
    }*/
}
