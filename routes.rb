get '/' do
  erb :front
end

get '/:fact' do
  if @fact = Fact.find(:first, :conditions => ["url = :url", {:url => params[:fact]}])
  else
    @fact = Fact.create(:url => params[:fact], :content => params[:fact].split('-').join(" ").capitalize)
  end
  "I read on the internet that "+@fact.content
end

# Use Sass with `views/style.scss` as your stylesheet
# To use, delete `public/stylehseets/style.css`
# get '/stylesheets/style.css' do
  # scss :style
# end

#   404
#---------------------------------------

not_found do
  erb :notfound
end