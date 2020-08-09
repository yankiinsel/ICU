//
//  MovieDetailView.swift
//  fo-training
//
//  Created by Yanki Insel on 13.03.2018.
//  Copyright © 2018 Fo. All rights reserved.
//

import UIKit

class MovieDetailView: NibView {
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var directorLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        prepareView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        prepareView()
    }
    
    private func prepareView() {
        
        backgroundColor = .clear
        prepareBlur()
        prepareTitleLabel()
        prepareDescriptionLabel()
        prepareDirectorLabel()
    }
    
    fileprivate func prepareBlur() {
        let blurEffect = UIBlurEffect(style: UIBlurEffectStyle.dark)
        let blurEffectView = UIVisualEffectView(effect: blurEffect)
        blurEffectView.frame = view.bounds
        blurEffectView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        addSubview(blurEffectView)
        sendSubview(toBack: blurEffectView)
    }
    
    fileprivate func prepareTitleLabel() {
        titleLabel.textColor = .white
        titleLabel.text = "Movie Title"
    }
    
    fileprivate func prepareDescriptionLabel() {
        descriptionLabel.textColor = Color.gray
        descriptionLabel.text = "Movie Description"
    }
    
    fileprivate func prepareDirectorLabel() {
        directorLabel.textColor = Color.gray
        directorLabel.text = "Director"
    }
    
}
