% 4. Два берега реки. На одном из них полицейский с заключенным, мама с дочерьми и отец с сыновьями. 
% Необходимо с помощью плота, вмещающего не более двух человек, переправить всех персонажей на другой берег реки. 
% Управлять плотом могут только полицейские и родители. Заключённого нельзя оставлять ни с одним из членов семьи. 
% Папе не разрешается находиться с дочерьми без присутствия матери. Маме не разрешается находиться с сыновьями без присутствия отца.
% Воложинец А.А. 221703

% Начало программы
run(Start, End) :-
    go(Start, End).

go(Start, End) :- start(Start, End, [Start]).

% Решение задачи. Выбираются безопасные переправления, пока задача не будет решена.
start(State, End, Path) :-
    move(State, NextState),
    not(unsafe(NextState)),
    not(member(NextState, Path)),
    nl,
    start(NextState, End, [NextState|Path]).

start(End, End, Path) :- print_solution(Path).

print_solution([State|Rest]) :-
    print_solution(Rest),
    [NextState|_] = Rest,
    print_move(NextState, State),
    write(State), nl.

print_solution([To, From]) :-
    nl,
    write(From), nl,
    print_move(From, To),
    write(To), nl.

% Переправление полицейского
move([X, X, Father, Mother, Son1, Son2, Daughter1, Daughter2],
     [Y, Y, Father, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление отца
move([X, Convicted, X, Mother, Son1, Son2, Daughter1, Daughter2],
     [Y, Convicted, Y, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление матери
move([X, Convicted, Father, X, Son1, Son2, Daughter1, Daughter2],
     [Y, Convicted, Father, Y, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление полицейского с преступником
move([X, Convicted, Father, Mother, X, Son2, Daughter1, Daughter2],
     [Y, Convicted, Father, Mother, Y, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление полицейского с отцом
move([X, Convicted, Father, Mother, Son1, X, Daughter1, Daughter2],
     [Y, Convicted, Father, Mother, Son1, Y, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление полицейского с матерью
move([X, Convicted, Father, Mother, Son1, Son2, X, Daughter2],
     [Y, Convicted, Father, Mother, Son1, Son2, Y, Daughter2]) :-
    opposite(X, Y).

% Переправление полицейского с первым сыном
move([X, Convicted, Father, Mother, Son1, Son2, Daughter1, X],
     [Y, Convicted, Father, Mother, Son1, Son2, Daughter1, Y]) :-
    opposite(X, Y).

% Переправление полицейского с вторым сыном
move([X, Convicted, Father, Mother, Son1, Son2, Daughter1, Daughter2],
     [Y, Convicted, Father, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление полицейского с первой дочерью
move([P, Convicted, X, X, Son1, Son2, Daughter1, Daughter2],
     [P, Convicted, Y, Y, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление полицейского с второй дочерью
move([P, Convicted, X, Mother, X, Son2, Daughter1, Daughter2],
     [P, Convicted, Y, Mother, Y, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление отца с матерью
move([P, Convicted, X, Mother, Son1, X, Daughter1, Daughter2],
     [P, Convicted, Y, Mother, Son1, Y, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление отца с первым сыном
move([P, Convicted, X, Mother, Son1, Son2, Daughter1, Daughter2],
     [P, Convicted, Y, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Переправление отца с вторым сыном
move([P, Convicted, Father, X, Son1, Son2, X, Daughter2],
     [P, Convicted, Father, Y, Son1, Son2, Y, Daughter2]) :-
    opposite(X, Y).

% Переправление матери с первой дочерью
move([P, Convicted, Father, X, Son1, Son2, Daughter1, X],
     [P, Convicted, Father, Y, Son1, Son2, Daughter1, Y]) :-
    opposite(X, Y).

% Переправление матери с второй дочерью
move([P, Convicted, Father, X, Son1, Son2, Daughter1, Daughter2],
     [P, Convicted, Father, Y, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(X, Y).

% Замена местами аргументов
opposite(left, right).
opposite(right, left).

% Определение небезопасных переправлений
unsafe([H, X, X,_,_,_,_, _]) :- opposite(H, X).
unsafe([H, X,_, X,_,_,_, _]) :- opposite(H, X).
unsafe([H, X,_,_, X,_,_, _]) :- opposite(H, X).
unsafe([H, X,_,_,_, X,_, _]) :- opposite(H, X).
unsafe([H, X,_,_,_,_, X, _]) :- opposite(H, X).
unsafe([H, X,_,_,_,_, _, X]) :- opposite(H, X).
unsafe([_,_, H, X, X,_,_, _]) :- opposite(H, X).
unsafe([_,_, H, X,_, X,_, _]) :- opposite(H, X).
unsafe([_,_, X, H,_,_, X, _]) :- opposite(H, X).
unsafe([_,_, X, H,_,_, _, X]) :- opposite(H, X).

print_move([From, From, Father, Mother, Son1, Son2, Daughter1, Daughter2],
    [To, To, Father, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Полицейский с заключённым переправляются на "),
    write_side(To).

print_move([From, Convicted, From, Mother, Son1, Son2, Daughter1, Daughter2],
    [To, Convicted, To, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Полицейский с отцом переправляются на "),
    write_side(To).

print_move([From, Convicted, Father, From, Son1, Son2, Daughter1, Daughter2],
    [To, Convicted, Father, To, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Полицейский с мамой переправляются на "),
    write_side(To).

print_move([From, Convicted, Father, Mother, From, Son2, Daughter1, Daughter2],
    [To, Convicted, Father, Mother, To, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Полицейский с первым сыном переправляются на "),
    write_side(To).

print_move([From, Convicted, Father, Mother, Son1, From, Daughter1, Daughter2],
    [To, Convicted, Father, Mother, Son1, To, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Полицейский с вторым сыном переправляются на "),
    write_side(To).

print_move([From, Convicted, Father, Mother, Son1, Son2, From, Daughter2],
    [To, Convicted, Father, Mother, Son1, Son2, To, Daughter2]) :-
    opposite(From, To),
    write("Полицейский с первой дочерью переправляются на "),
    write_side(To).

print_move([From, Convicted, Father, Mother, Son1, Son2, Daughter1, From],
    [To, Convicted, Father, Mother, Son1, Son2, Daughter1, To]) :-
    opposite(From, To),
    write("Полицейский с второй дочерью переправляются на "),
    write_side(To).

print_move([Policeman, Convicted, Father, From, Son1, Son2, From, Daughter2],
    [Policeman, Convicted, Father, To, Son1, Son2, To, Daughter2]) :-
    opposite(From, To),
    write("Мама с первой дочерью переправляются на "),
    write_side(To).

print_move([Policeman, Convicted, Father, From, Son1, Son2, Daughter1, From],
    [Policeman, Convicted, Father, To, Son1, Son2, Daughter1, To]) :-
    opposite(From, To),
    write("Мама с второй дочерью переправляются на "),
    write_side(To).

print_move([Policeman, Convicted, From, From, Son1, Son2, Daughter1, Daughter2],
    [Policeman, Convicted, To, To, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Мама с отцом переправляются на "),
    write_side(To).

print_move([Policeman, Convicted, From, Mother, From, Son2, Daughter1, Daughter2],
    [Policeman, Convicted, From, Mother, To, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Отец с первым сыном переправляются на "),
    write_side(To).

print_move([Policeman, Convicted, From, Mother, Son1, From, Daughter1, Daughter2],
    [Policeman, Convicted, To, Mother, Son1, To, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Отец с вторым сыном переправляются на "),
    write_side(To).

print_move([From, Convicted, Father, Mother, Son1, Son2, Daughter1, Daughter2],
    [To, Convicted, Father, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Полицейский переправляется на "),
    write_side(To).

print_move([Policeman, Convicted, Father, Mother, Son1, Son2, Daughter1, Daughter2],
    [Policeman, Convicted, To, Mother, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Отец переправляется на "),
    write_side(To).

print_move([Policeman, Convicted, Father, From, Son1, Son2, Daughter1, Daughter2],
    [Policeman, Convicted, Father, To, Son1, Son2, Daughter1, Daughter2]) :-
    opposite(From, To),
    write("Мама переправляется на "),
    write_side(To).

%dummy print
print_move([_, _, _, _, _, _, _, _], [_, _, _, _, _, _, _, _]).

write_side(left) :-
    write("начальный берег."), nl.
write_side(right) :-
    write("конечный берег."), nl.

% Запуск программы с передачей начального и конечного состояния
% :- run([left,left,left,left,left,left,left,left], [right,right,right,right,right,right,right,right]).
