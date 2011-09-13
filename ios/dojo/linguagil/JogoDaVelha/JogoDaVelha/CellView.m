//
//  Casa11View.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "CellView.h"

@implementation CellView

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    [[NSNotificationCenter defaultCenter] postNotificationName:@"teste" object:self];
}

- (int)linha
{
    int linha;
    
    float center = self.frame.origin.y + self.frame.size.height / 2;
    float section = self.window.frame.size.height / 3;
    
    for(int i = 1; i <= 3; i ++){
        if(center > section * (i-1) && center < section * (i)){
            linha = i;
            break;
        }
    }

    return linha;  
}

- (int)coluna
{
    int coluna;
    
    float center = self.frame.origin.x + self.frame.size.width / 2;
    float section = self.window.frame.size.width / 3;
    
    for(int i = 1; i <= 3; i ++){
        if(center > section * (i-1) && center < section * (i)){
            coluna = i;
            break;
        }
    }

    return coluna;
}

@end
