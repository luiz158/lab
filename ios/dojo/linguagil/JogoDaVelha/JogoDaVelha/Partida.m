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

@implementation Partida

- (void)limparTabuleiro
{
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            tabuleiro[i][j] = Ninguem;
        }
    }
}

- (id)init
{
    self = [super init];
    if (self) {
        jogador = Jogador1;
        
        [self limparTabuleiro];
    }
    
    return self;
}

- (Jogador)donoDaLinha: (int)linha eColuna: (int)coluna
{
    return tabuleiro[linha - 1][coluna-1];
}

- (void)alternarJogador {
    jogador = (jogador == Jogador1 ? Jogador2 : Jogador1);
}


- (BOOL)casaInvalida: (int)linha eColuna: (int)coluna
{
    return linha < 1 || linha > 3 || coluna < 1 || coluna > 3;
}

- (int)quantidadeDeCasasVazias
{
    int vazios = 0;
    
    for(int linha = 1; linha <= 3; linha++) {
        for(int coluna = 1; coluna <= 3; coluna++) {
            if([self donoDaLinha:linha eColuna:coluna] == Ninguem){
                vazios++;
            }
        }
    }
    
    return vazios;
}

- (void)marcarLinha: (int)linha eColuna: (int)coluna
{
    
    if([self casaInvalida:linha eColuna:coluna]) {
        @throw [[CasaInexistenteException alloc] init];        
    }
    
    if([self donoDaLinha:linha eColuna:coluna] != Ninguem) {
        @throw [[CasaJaMarcadaException alloc] init];
    }
    
    if([self vencedorDaPartida] != JogoEmAndamento){
        @throw [[JogoEncerradoException alloc] init];        
    }
    
    tabuleiro[linha - 1][coluna-1] = [self proximoJogador];
    
    [self alternarJogador];
}


- (Jogador)proximoJogador
{
    return jogador;
}


- (Jogador)vencedorDaPartida
{
    Jogador vencedor;


    if([self donoDaLinha:1 eColuna:1] != Ninguem && 
       [self donoDaLinha:1 eColuna:1] == [self donoDaLinha:1 eColuna:2] &&
       [self donoDaLinha:1 eColuna:1] == [self donoDaLinha:1 eColuna:3]) {
        vencedor = [self donoDaLinha:1 eColuna:1];
    }
    
    else if([self donoDaLinha:2 eColuna:1] != Ninguem && 
            [self donoDaLinha:2 eColuna:1] == [self donoDaLinha:2 eColuna:2] &&
            [self donoDaLinha:2 eColuna:1] == [self donoDaLinha:2 eColuna:3]) {
        vencedor = [self donoDaLinha:2 eColuna:1];
    }
    
    else if([self donoDaLinha:3 eColuna:1] != Ninguem && 
            [self donoDaLinha:3 eColuna:1] == [self donoDaLinha:3 eColuna:2] &&
            [self donoDaLinha:3 eColuna:1] == [self donoDaLinha:3 eColuna:3]) {
        vencedor = [self donoDaLinha:3 eColuna:1];
    }
    
    
    else if([self donoDaLinha:1 eColuna:1] != Ninguem && 
            [self donoDaLinha:1 eColuna:1] == [self donoDaLinha:2 eColuna:1] &&
            [self donoDaLinha:1 eColuna:1] == [self donoDaLinha:3 eColuna:1]) {
        vencedor = [self donoDaLinha:1 eColuna:1];
    }
    
    else if([self donoDaLinha:1 eColuna:2] != Ninguem && 
            [self donoDaLinha:1 eColuna:2] == [self donoDaLinha:2 eColuna:2] &&
            [self donoDaLinha:1 eColuna:2] == [self donoDaLinha:3 eColuna:2]) {
        vencedor = [self donoDaLinha:1 eColuna:2];
    }
    
    else if([self donoDaLinha:1 eColuna:3] != Ninguem && 
            [self donoDaLinha:1 eColuna:3] == [self donoDaLinha:2 eColuna:3] &&
            [self donoDaLinha:1 eColuna:3] == [self donoDaLinha:3 eColuna:3]) {
        vencedor = [self donoDaLinha:1 eColuna:3];
    }
    
    
    else if([self donoDaLinha:1 eColuna:1] != Ninguem && 
            [self donoDaLinha:1 eColuna:1] == [self donoDaLinha:2 eColuna:2] &&
            [self donoDaLinha:1 eColuna:1] == [self donoDaLinha:3 eColuna:3]) {
        vencedor = [self donoDaLinha:1 eColuna:1];
    }
    
    else if([self donoDaLinha:1 eColuna:3] != Ninguem && 
            [self donoDaLinha:1 eColuna:3] == [self donoDaLinha:2 eColuna:2] &&
            [self donoDaLinha:1 eColuna:3] == [self donoDaLinha:3 eColuna:1]) {
        vencedor = [self donoDaLinha:1 eColuna:3];
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
            [description appendFormat:@" %i ", [self donoDaLinha:linha eColuna:coluna]];
        }
    }
    
    return description;
}

@end
