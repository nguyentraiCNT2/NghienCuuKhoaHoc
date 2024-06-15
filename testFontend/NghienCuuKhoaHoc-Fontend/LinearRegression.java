public class LinearRegression {
    public static void main(String[] args) {
        // Bước 1: Chuẩn bị dữ liệu
        double[][] X = {
            {523, 1},
            {645, 1},
            {708, 2},
            {1034, 3},
            {2290, 4},
            {2545, 4}
        };
        
        double[] y = {100, 150, 200, 300, 350, 440};
        
        // Bước 2: Chuẩn hóa dữ liệu
        int m = X.length;
        int n = X[0].length;

        double[] X_mean = new double[n];
        double[] X_std = new double[n];
        double y_mean = 0.0;
        double y_std = 0.0;
        
        // Tính toán mean và std cho X và y
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                X_mean[j] += X[i][j];
            }
            X_mean[j] /= m;
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                X_std[j] += Math.pow(X[i][j] - X_mean[j], 2);
            }
            X_std[j] = Math.sqrt(X_std[j] / m);
        }
        
        for (int i = 0; i < m; i++) {
            y_mean += y[i];
        }
        y_mean /= m;

        for (int i = 0; i < m; i++) {
            y_std += Math.pow(y[i] - y_mean, 2);
        }
        y_std = Math.sqrt(y_std / m);

        // Chuẩn hóa X và y
        double[][] X_norm = new double[m][n];
        double[] y_norm = new double[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                X_norm[i][j] = (X[i][j] - X_mean[j]) / X_std[j];
            }
            y_norm[i] = (y[i] - y_mean) / y_std;
        }

        // Bước 3: Khởi tạo các tham số
        double[] w = new double[n];
        double b = 0.0;
        double learningRate = 0.01;
        int numIterations = 1000;

        // Bước 4: Cập nhật các tham số sử dụng Gradient Descent
        for (int iter = 0; iter < numIterations; iter++) {
            double[] y_hat = new double[m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    y_hat[i] += w[j] * X_norm[i][j];
                }
                y_hat[i] += b;
            }

            double[] dw = new double[n];
            double db = 0.0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dw[j] += (y_hat[i] - y_norm[i]) * X_norm[i][j];
                }
                db += (y_hat[i] - y_norm[i]);
            }
            for (int j = 0; j < n; j++) {
                dw[j] /= m;
                w[j] -= learningRate * dw[j];
            }
            db /= m;
            b -= learningRate * db;

            // Tính toán và in ra hàm mất mát sau mỗi 100 lần lặp
            if (iter % 100 == 0) {
                double loss = 0.0;
                for (int i = 0; i < m; i++) {
                    double prediction = 0.0;
                    for (int j = 0; j < n; j++) {
                        prediction += w[j] * X_norm[i][j];
                    }
                    prediction += b;
                    loss += Math.pow(prediction - y_norm[i], 2);
                }
                loss /= (2 * m);
                System.out.println("Iteration " + iter + ": Loss = " + loss);
            }
        }

        // Bước 5: Hiển thị kết quả cuối cùng
        System.out.println("Final parameters:");
        for (int j = 0; j < n; j++) {
            System.out.println("w[" + j + "] = " + w[j]);
        }
        System.out.println("b = " + b);

        // Dự đoán giá trị mới
        double[] newHouse = {1500, 3};
        double[] newHouseNorm = new double[n];
        for (int j = 0; j < n; j++) {
            newHouseNorm[j] = (newHouse[j] - X_mean[j]) / X_std[j];
        }

        double predictedPriceNorm = 0.0;
        for (int j = 0; j < n; j++) {
            predictedPriceNorm += w[j] * newHouseNorm[j];
        }
        predictedPriceNorm += b;
        double predictedPrice = predictedPriceNorm * y_std + y_mean;

        System.out.println("Predicted price for house with 1500 square feet and 3 bedrooms: " + predictedPrice + " (1000$)");
    }
}
