//
//  JogoDaVelhaTests.m
//  JogoDaVelhaTests
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "PartidaTests.h"
#import "CasaJaMarcadaException.h"
#import "CasaInexistenteException.h"
#import "JogoEncerradoException.h"

@implementation PartidaTests

- (void)setUp
{
    [super setUp];
    
    _partida = [[Partida alloc] init];
}

- (void)tearDown
{
    NSLog(@"%@", _partida);
    [_partida release];
    
    [super tearDown];
}

- (void)testVerificarSeTabuleiroIniciaVazio
{
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:1 eColuna:1], nil);
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:1 eColuna:2], nil);    
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:1 eColuna:3], nil);
    
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:2 eColuna:1], nil);
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:2 eColuna:2], nil);    
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:2 eColuna:3], nil);
    
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:3 eColuna:1], nil);
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:3 eColuna:2], nil);    
    STAssertEquals(Ninguem, [_partida jogadorDaLinha:3 eColuna:3], nil);
}

- (void)testFalharAoMarcarCasaInexistente
{
    @try {
        [_partida marcarLinha:-1 eColuna:1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    }
    
    @try {
        [_partida marcarLinha:0 eColuna:1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    }
    
    @try {
        [_partida marcarLinha:4 eColuna:1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    }
    
    @try {
        [_partida marcarLinha:1 eColuna:-1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    }
    
    @try {
        [_partida marcarLinha:1 eColuna:0];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    }
    
    @try {
        [_partida marcarLinha:1 eColuna:4];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    }
}

- (void)testMarcarCasaVaziaComSucesso
{
    [_partida marcarLinha:2 eColuna:1];
    STAssertEquals(Jogador1, [_partida jogadorDaLinha:2 eColuna:1], nil);
    
    [_partida marcarLinha:3 eColuna:2];
    STAssertEquals(Jogador2, [_partida jogadorDaLinha:3 eColuna:2], nil);
}

- (void)testFalharAoMarcarCasaRepetida
{
    [_partida marcarLinha:3 eColuna:3];
    
    @try {
        [_partida marcarLinha:3 eColuna:3];
        STFail(@"Marcou casa já marcada!");
    }
    @catch (CasaJaMarcadaException *exception) {
        STAssertEquals(Jogador1, [_partida jogadorDaLinha:3 eColuna:3], nil);
    }
    
    [_partida marcarLinha:2 eColuna:1];
    
    @try {
        [_partida marcarLinha:2 eColuna:1];
        STFail(@"Marcou casa já marcada!");
    }
    @catch (CasaJaMarcadaException *exception) {
        STAssertEquals(Jogador2, [_partida jogadorDaLinha:2 eColuna:1], nil);
    }
    
    @try {
        [_partida marcarLinha:2 eColuna:1];
        STFail(@"Marcou casa já marcada!");
    }
    @catch (CasaJaMarcadaException *exception) {
        STAssertEquals(Jogador2, [_partida jogadorDaLinha:2 eColuna:1], nil);
    }
}

- (void)testAlternanciaAutomaticaDeJogador
{
    STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    
    [_partida marcarLinha:1 eColuna:1];
    STAssertEquals(Jogador2, [_partida proximoJogador], nil);
    
    [_partida marcarLinha:1 eColuna:2];
    STAssertEquals(Jogador1, [_partida proximoJogador], nil);
    
    STAssertEquals(Jogador1, [_partida proximoJogador], nil);
}

- (void)testPartidaEmAndamento
{
    STAssertEquals(JogoEmAndamento, [_partida vencedorDaPartida], nil);
    
    [_partida marcarLinha:1 eColuna:2];
    STAssertEquals(JogoEmAndamento, [_partida vencedorDaPartida], nil);
    
    [_partida marcarLinha:2 eColuna:2];
    STAssertEquals(JogoEmAndamento, [_partida vencedorDaPartida], nil);    
    
    [_partida marcarLinha:2 eColuna:3];
    STAssertEquals(JogoEmAndamento, [_partida vencedorDaPartida], nil);    
}

- (void)testJogador1Venceu
{
    [_partida marcarLinha:1 eColuna:1];
    [_partida marcarLinha:1 eColuna:2];
    
    [_partida marcarLinha:2 eColuna:1];
    [_partida marcarLinha:2 eColuna:2];
    
    [_partida marcarLinha:3 eColuna:1];
    
    STAssertEquals(Jogador1, [_partida vencedorDaPartida], nil);
    
    [_partida init];
    
    [_partida marcarLinha:1 eColuna:3];
    [_partida marcarLinha:1 eColuna:2];
    
    [_partida marcarLinha:2 eColuna:2];
    [_partida marcarLinha:2 eColuna:3];
    
    [_partida marcarLinha:3 eColuna:1];
    
    STAssertEquals(Jogador1, [_partida vencedorDaPartida], nil);
}

- (void)testJogador2Venceu
{
    [_partida marcarLinha:1 eColuna:1];
    [_partida marcarLinha:1 eColuna:3];
    
    [_partida marcarLinha:2 eColuna:2];
    [_partida marcarLinha:2 eColuna:3];
    
    [_partida marcarLinha:3 eColuna:1];
    [_partida marcarLinha:3 eColuna:3];
    
    STAssertEquals(Jogador2, [_partida vencedorDaPartida], nil);
}

- (void)testEmpate
{
    [_partida marcarLinha:1 eColuna:1];
    [_partida marcarLinha:2 eColuna:2];

    [_partida marcarLinha:1 eColuna:2];
    [_partida marcarLinha:1 eColuna:3];

    [_partida marcarLinha:3 eColuna:1];
    [_partida marcarLinha:2 eColuna:1];

    [_partida marcarLinha:2 eColuna:3];
    [_partida marcarLinha:3 eColuna:2];

    [_partida marcarLinha:3 eColuna:3];

    STAssertEquals(Ninguem, [_partida vencedorDaPartida], nil);
}

- (void)testFalharAoJogarAposPartidaFinalizada
{
    [_partida marcarLinha:1 eColuna:1];
    [_partida marcarLinha:1 eColuna:3];
    
    [_partida marcarLinha:2 eColuna:2];
    [_partida marcarLinha:2 eColuna:3];
    
    [_partida marcarLinha:3 eColuna:1];
    [_partida marcarLinha:3 eColuna:3];
    
    @try {
        [_partida marcarLinha:2 eColuna:1];
         STFail(@"Não pode jogar após a partida finalizada!");
    }
    @catch (JogoEncerradoException *exception) {
    }
}

@end
