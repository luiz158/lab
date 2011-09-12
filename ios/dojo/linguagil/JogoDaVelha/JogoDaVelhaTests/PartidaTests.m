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
    
    partida = [[Partida alloc] init];
}

- (void)tearDown
{
    NSLog(@"%@", partida);
    [partida release];
    
    [super tearDown];
}

- (void)testVerificarSeTabuleiroIniciaVazio
{
    STAssertEquals(Ninguem, [partida donoDaLinha:1 eColuna:1], nil);
    STAssertEquals(Ninguem, [partida donoDaLinha:1 eColuna:2], nil);    
    STAssertEquals(Ninguem, [partida donoDaLinha:1 eColuna:3], nil);
    
    STAssertEquals(Ninguem, [partida donoDaLinha:2 eColuna:1], nil);
    STAssertEquals(Ninguem, [partida donoDaLinha:2 eColuna:2], nil);    
    STAssertEquals(Ninguem, [partida donoDaLinha:2 eColuna:3], nil);
    
    STAssertEquals(Ninguem, [partida donoDaLinha:3 eColuna:1], nil);
    STAssertEquals(Ninguem, [partida donoDaLinha:3 eColuna:2], nil);    
    STAssertEquals(Ninguem, [partida donoDaLinha:3 eColuna:3], nil);
}

- (void)testFalharAoMarcarCasaInexistente
{
    @try {
        [partida marcarLinha:-1 eColuna:1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [partida proximoJogador], nil);
    }
    
    @try {
        [partida marcarLinha:0 eColuna:1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [partida proximoJogador], nil);
    }
    
    @try {
        [partida marcarLinha:4 eColuna:1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [partida proximoJogador], nil);
    }
    
    @try {
        [partida marcarLinha:1 eColuna:-1];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [partida proximoJogador], nil);
    }
    
    @try {
        [partida marcarLinha:1 eColuna:0];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [partida proximoJogador], nil);
    }
    
    @try {
        [partida marcarLinha:1 eColuna:4];
        STFail(@"Marcou uma casa inexistente!");
    }
    @catch (CasaInexistenteException *exception) {
        STAssertEquals(Jogador1, [partida proximoJogador], nil);
    }
}

- (void)testMarcarCasaVaziaComSucesso
{
    [partida marcarLinha:2 eColuna:1];
    STAssertEquals(Jogador1, [partida donoDaLinha:2 eColuna:1], nil);
    
    [partida marcarLinha:3 eColuna:2];
    STAssertEquals(Jogador2, [partida donoDaLinha:3 eColuna:2], nil);
}

- (void)testFalharAoMarcarCasaPreenchida
{
    [partida marcarLinha:3 eColuna:3];
    
    @try {
        [partida marcarLinha:3 eColuna:3];
        STFail(@"Marcou casa já marcada!");
    }
    @catch (CasaJaMarcadaException *exception) {
        STAssertEquals(Jogador1, [partida donoDaLinha:3 eColuna:3], nil);
    }
    
    [partida marcarLinha:2 eColuna:1];
    
    @try {
        [partida marcarLinha:2 eColuna:1];
        STFail(@"Marcou casa já marcada!");
    }
    @catch (CasaJaMarcadaException *exception) {
        STAssertEquals(Jogador2, [partida donoDaLinha:2 eColuna:1], nil);
    }
    
    @try {
        [partida marcarLinha:2 eColuna:1];
        STFail(@"Marcou casa já marcada!");
    }
    @catch (CasaJaMarcadaException *exception) {
        STAssertEquals(Jogador2, [partida donoDaLinha:2 eColuna:1], nil);
    }
}

- (void)testAlternanciaAutomaticaDeJogador
{
    STAssertEquals(Jogador1, [partida proximoJogador], nil);
    
    [partida marcarLinha:1 eColuna:1];
    STAssertEquals(Jogador2, [partida proximoJogador], nil);
    
    [partida marcarLinha:1 eColuna:2];
    STAssertEquals(Jogador1, [partida proximoJogador], nil);
    
    STAssertEquals(Jogador1, [partida proximoJogador], nil);
}

- (void)testPartidaEmandamento
{
    STAssertEquals(JogoEmAndamento, [partida vencedorDaPartida], nil);
    
    [partida marcarLinha:1 eColuna:2];
    STAssertEquals(JogoEmAndamento, [partida vencedorDaPartida], nil);
    
    [partida marcarLinha:2 eColuna:2];
    STAssertEquals(JogoEmAndamento, [partida vencedorDaPartida], nil);    
    
    [partida marcarLinha:2 eColuna:3];
    STAssertEquals(JogoEmAndamento, [partida vencedorDaPartida], nil);    
}

- (void)testJogador1Venceu
{
    [partida marcarLinha:1 eColuna:1];
    [partida marcarLinha:1 eColuna:2];
    
    [partida marcarLinha:2 eColuna:1];
    [partida marcarLinha:2 eColuna:2];
    
    [partida marcarLinha:3 eColuna:1];
    
    STAssertEquals(Jogador1, [partida vencedorDaPartida], nil);
    
    [partida init];
    
    [partida marcarLinha:1 eColuna:3];
    [partida marcarLinha:1 eColuna:2];
    
    [partida marcarLinha:2 eColuna:2];
    [partida marcarLinha:2 eColuna:3];
    
    [partida marcarLinha:3 eColuna:1];
    
    STAssertEquals(Jogador1, [partida vencedorDaPartida], nil);
}

- (void)testJogador2Venceu
{
    [partida marcarLinha:1 eColuna:1];
    [partida marcarLinha:1 eColuna:3];
    
    [partida marcarLinha:2 eColuna:2];
    [partida marcarLinha:2 eColuna:3];
    
    [partida marcarLinha:3 eColuna:1];
    [partida marcarLinha:3 eColuna:3];
    
    STAssertEquals(Jogador2, [partida vencedorDaPartida], nil);
}

- (void)testEmpate
{
    [partida marcarLinha:1 eColuna:1];
    [partida marcarLinha:2 eColuna:2];

    [partida marcarLinha:1 eColuna:2];
    [partida marcarLinha:1 eColuna:3];

    [partida marcarLinha:3 eColuna:1];
    [partida marcarLinha:2 eColuna:1];

    [partida marcarLinha:2 eColuna:3];
    [partida marcarLinha:3 eColuna:2];

    [partida marcarLinha:3 eColuna:3];

    STAssertEquals(Ninguem, [partida vencedorDaPartida], nil);
}

- (void)testFalharAoJogarAposPartidaFinalizada
{
    [partida marcarLinha:1 eColuna:1];
    [partida marcarLinha:1 eColuna:3];
    
    [partida marcarLinha:2 eColuna:2];
    [partida marcarLinha:2 eColuna:3];
    
    [partida marcarLinha:3 eColuna:1];
    [partida marcarLinha:3 eColuna:3];
    
    @try {
        [partida marcarLinha:2 eColuna:1];
         STFail(@"Não pode jogar após a partida finalizada!");
    }
    @catch (JogoEncerradoException *exception) {
    }
}

@end
