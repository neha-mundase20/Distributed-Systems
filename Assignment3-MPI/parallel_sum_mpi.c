#include <stdio.h>
#include <mpi.h>

int main(int argc, char *argv[]) {
    int rank, size;
    int N = 12; // Total number of elements
    int data[12] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    
    MPI_Init(&argc, &argv);               // Initialize MPI
    MPI_Comm_rank(MPI_COMM_WORLD, &rank); // Get current process rank
    MPI_Comm_size(MPI_COMM_WORLD, &size); // Get total number of processes

    int elements_per_proc = N / size;
    int sub_array[elements_per_proc];
    
    // Scatter the array to all processes
    MPI_Scatter(data, elements_per_proc, MPI_INT,
                sub_array, elements_per_proc, MPI_INT,
                0, MPI_COMM_WORLD);

    // Each process computes its partial sum
    int partial_sum = 0;
    for (int i = 0; i < elements_per_proc; i++) {
        partial_sum += sub_array[i];
    }

    printf("Process %d: Partial Sum = %d\n", rank, partial_sum);

    // Gather all partial sums to the master process
    int total_sum = 0;
    MPI_Reduce(&partial_sum, &total_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    // Only master process prints the final sum
    if (rank == 0) {
        printf("Total Sum = %d\n", total_sum);
    }

    MPI_Finalize(); // Finalize MPI
    return 0;
}

