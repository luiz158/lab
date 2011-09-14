//
//  Partida.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Partida.h"
#import "CasaJaMarcadaException.h"
#import "CasaInexistenteException.h"
#import "JogoEncerradoException.h"


@interface Partida (PrivateMethods)

- (void)limparTabuleiro;

- (void)alternarJogador;

- (BOOL)casaInvalidaNaLinha: (int)linha eColuna: (int)coluna;

- (int)quantidadeDeCasasVazias;

@end


@implementation Partida

- (id)init
{
    self = [super init];
    if (self) {
        [self reiniciar];
    }
    
    return self;
}

- (void)reiniciar
{
    _proximoJogador = Jogador1;
    [self limparTabuleiro];
}

- (void)limparTabuleiro
{
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            _tabuleiro[i][j] = Ninguem;
        }
    }
}

- (Jogador)jogadorDaLinha: (int)linha eColuna: (int)coluna
{
    return _tabuleiro[linha - 1][coluna-1];
}

- (void)alternarJogador {
    _proximoJogador = (_proximoJogador == Jogador1 ? Jogador2 : Jogador1);
}


- (BOOL)casaInvalidaNaLinha: (int)linha eColuna: (int)coluna
{
    return linha < 1 || linha > 3 || coluna < 1 || coluna > 3;
}

- (int)quantidadeDeCasasVazias
{
    int vazios = 0;
    
    for(int linha = 1; linha <= 3; linha++) {
        for(int coluna = 1; coluna <= 3; coluna++) {
            if([self jogadorDaLinha:linha eColuna:coluna] == Ninguem){
                vazios++;
            }
        }
    }
    
    return vazios;
}

- (void)marcarLinha: (int)linha eColuna: (int)coluna
{
    
    if([self casaInvalidaNaLinha:linha eColuna:coluna]) {
        @throw [[CasaInexistenteException alloc] init];        
    }
    
    if([self jogadorDaLinha:linha eColuna:coluna] != Ninguem) {
        @throw [[CasaJaMarcadaException alloc] init];
    }
    
    if([self vencedorDaPartida] != JogoEmAndamento){
        @throw [[JogoEncerradoException alloc] init];        
    }
    
    _tabuleiro[linha - 1][coluna-1] = [self proximoJogador];
    
    if([self vencedorDaPartida] == JogoEmAndamento){
        [self alternarJogador];        
    }
}


- (Jogador)proximoJogador
{
    return _proximoJogador;
}


- (Jogador)vencedorDaPartida
{
    Jogador vencedor;
    
    
    if([self jogadorDaLinha:1 eColuna:1] != Ninguem && 
       [self jogadorDaLinha:1 eColuna:1] == [self jogadorDaLinha:1 eColuna:2] &&
       [self jogadorDaLinha:1 eColuna:1] == [self jogadorDaLinha:1 eColuna:3]) {
        vencedor = [self jogadorDaLinha:1 eColuna:1];
    }
    
    else if([self jogadorDaLinha:2 eColuna:1] != Ninguem && 
            [self jogadorDaLinha:2 eColuna:1] == [self jogadorDaLinha:2 eColuna:2] &&
            [self jogadorDaLinha:2 eColuna:1] == [self jogadorDaLinha:2 eColuna:3]) {
        vencedor = [self jogadorDaLinha:2 eColuna:1];
    }
    
    else if([self jogadorDaLinha:3 eColuna:1] != Ninguem && 
            [self jogadorDaLinha:3 eColuna:1] == [self jogadorDaLinha:3 eColuna:2] &&
            [self jogadorDaLinha:3 eColuna:1] == [self jogadorDaLinha:3 eColuna:3]) {
        vencedor = [self jogadorDaLinha:3 eColuna:1];
    }
    
    
    else if([self jogadorDaLinha:1 eColuna:1] != Ninguem && 
            [self jogadorDaLinha:1 eColuna:1] == [self jogadorDaLinha:2 eColuna:1] &&
            [self jogadorDaLinha:1 eColuna:1] == [self jogadorDaLinha:3 eColuna:1]) {
        vencedor = [self jogadorDaLinha:1 eColuna:1];
    }
    
    else if([self jogadorDaLinha:1 eColuna:2] != Ninguem && 
            [self jogadorDaLinha:1 eColuna:2] == [self jogadorDaLinha:2 eColuna:2] &&
            [self jogadorDaLinha:1 eColuna:2] == [self jogadorDaLinha:3 eColuna:2]) {
        vencedor = [self jogadorDaLinha:1 eColuna:2];
    }
    
    else if([self jogadorDaLinha:1 eColuna:3] != Ninguem && 
            [self jogadorDaLinha:1 eColuna:3] == [self jogadorDaLinha:2 eColuna:3] &&
            [self jogadorDaLinha:1 eColuna:3] == [self jogadorDaLinha:3 eColuna:3]) {
        vencedor = [self jogadorDaLinha:1 eColuna:3];
    }
    
    
    else if([self jogadorDaLinha:1 eColuna:1] != Ninguem && 
            [self jogadorDaLinha:1 eColuna:1] == [self jogadorDaLinha:2 eColuna:2] &&
            [self jogadorDaLinha:1 eColuna:1] == [self jogadorDaLinha:3 eColuna:3]) {
        vencedor = [self jogadorDaLinha:1 eColuna:1];
    }
    
    else if([self jogadorDaLinha:1 eColuna:3] != Ninguem && 
            [self jogadorDaLinha:1 eColuna:3] == [self jogadorDaLinha:2 eColuna:2] &&
            [self jogadorDaLinha:1 eColuna:3] == [self jogadorDaLinha:3 eColuna:1]) {
        vencedor = [self jogadorDaLinha:1 eColuna:3];
    }
    
    else if([self quantidadeDeCasasVazias] > 0){
        vencedor = JogoEmAndamento;
    }
    
    else {
        vencedor = Ninguem;
    }
    
    
    return vencedor;
}

- (NSString *)description {
    NSMutableString *description = [[NSMutableString alloc] init];
    
    for(int linha = 1; linha <= 3; linha++) {
        [description appendString:@"\n"];
        
        for(int coluna = 1; coluna <= 3; coluna++) {
            [description appendFormat:@" %i ", [self jogadorDaLinha:linha eColuna:coluna]];
        }
    }
    
    return description;
}

@end
