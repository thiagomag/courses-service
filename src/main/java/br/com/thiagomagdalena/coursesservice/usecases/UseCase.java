package br.com.thiagomagdalena.coursesservice.usecases;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
